package com.bta.diplom.service.impl;

import com.bta.diplom.dto.UserAccountDto;
import com.bta.diplom.email.EmailSender;
import com.bta.diplom.mapper.WebMapper;
import com.bta.diplom.model.ActivationLink;
import com.bta.diplom.model.UserAccount;
import com.bta.diplom.repository.ActivationLinkRepository;
import com.bta.diplom.repository.UserAccountRepository;
import com.bta.diplom.service.UserAccountService;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {
  @Autowired
  private WebMapper<UserAccountDto, UserAccount> webMapper;
  @Autowired
  private UserAccountRepository userAccountRepository;
  @Autowired
  private ActivationLinkRepository activationLinkRepository;

  @Autowired
  private EmailSender emailSender;

  @Transactional
  @Override
  public void create(UserAccountDto userAccountDto) {
    checkIfUserAccountExist(userAccountDto);
    final UserAccount newUserAccount = webMapper.toEntity(userAccountDto);
    processNewUserAccount(newUserAccount);
    final UserAccount savedUserAccount = userAccountRepository.save(newUserAccount);
    final ActivationLink activationLink = createActivationLink(savedUserAccount);
    activationLinkRepository.save(activationLink);
    final String link =
        "http://localhost:8081/user-account/activate?code=" + activationLink.getCode();
    emailSender.sendEmail(
        userAccountDto.getEmail(), link, "Please activate your Account in Diploma project");
  }

  private ActivationLink createActivationLink(UserAccount userAccount) {
    return ActivationLink.builder()
        .created(ZonedDateTime.now())
        .code(UUID.randomUUID().toString())
        .userAccount(userAccount)
        .build();
  }

  private void checkIfUserAccountExist(UserAccountDto userAccountDto) {
    final var username = userAccountDto.getUsername();
    final var userAccountFromDB =
        userAccountRepository.findByUsernameAndActive(username, false);
    if (userAccountFromDB != null) {
      throw new RuntimeException("User with username: " + username + " already registered!");
    }
  }

  private void processNewUserAccount(UserAccount newUserAccount) {
    newUserAccount.setActive(false);
    newUserAccount.setCreated(ZonedDateTime.now());
  }
}

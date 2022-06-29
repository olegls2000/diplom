package com.bta.diplom.service.impl;

import com.bta.diplom.dto.UserAccountDto;
import com.bta.diplom.email.EmailService;
import com.bta.diplom.mapper.WebMapper;
import com.bta.diplom.model.ActivationLink;
import com.bta.diplom.model.UserAccount;
import com.bta.diplom.repository.ActivationLinkRepository;
import com.bta.diplom.repository.UserAccountRepository;
import com.bta.diplom.service.UserAccountService;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
  private EmailService emailSender;

  @Autowired
  private PasswordEncoder passwordEncoder;

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

  @Transactional
  @Override
  public void activate(String code) {
    if (code == null || code.isEmpty()) {
      throw new RuntimeException("Activation code must not be null or Empty!");
    }
    final var activationLink = activationLinkRepository.findByCode(code);
    checkActivationLink(activationLink, code);
    activationLink.getUserAccount().setActive(true);
    activationLinkRepository.delete(activationLink);
  }

  private void checkActivationLink(ActivationLink activationLink, String code) {
    if (activationLink == null) {
      throw new RuntimeException("Invalid code in activation link: " + code);
    }
    final Duration between = Duration.between(
        activationLink.getCreated().toInstant(), ZonedDateTime.now().toInstant()
    );
    final long waitingPeriodInDays = between.toDays();
    if (waitingPeriodInDays >= 1) {
      throw new RuntimeException("Activation link with code: " + code + " already expired");
    }
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
    //newUserAccount.setActive(false);
    newUserAccount.setCreated(ZonedDateTime.now());
    final String encryptedPassword = passwordEncoder.encode(newUserAccount.getPassword());
    newUserAccount.setPassword(encryptedPassword);
    newUserAccount.setActive(true);
  }
}

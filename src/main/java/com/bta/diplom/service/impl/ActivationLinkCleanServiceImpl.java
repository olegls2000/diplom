package com.bta.diplom.service.impl;

import com.bta.diplom.model.ActivationLink;
import com.bta.diplom.repository.ActivationLinkRepository;
import com.bta.diplom.service.ActivationLinkCleanService;
import java.time.ZonedDateTime;
import java.util.List;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ActivationLinkCleanServiceImpl implements ActivationLinkCleanService {

  @Autowired
  private ActivationLinkRepository activationLinkRepository;


  @Scheduled(initialDelay = 5_000l, fixedRate = 10_000l)
  @Transactional
  @Override
  public void clean() {
    final var checkDate = ZonedDateTime.now().minusDays(1l);
    final var expiredLinks = activationLinkRepository.findByCreatedBefore(checkDate);
    if (expiredLinks.isEmpty()) {
      log.info("No expired Activation Links in System.");
      return;
    }
    log.info("Detected " + expiredLinks.size() + " Activation Links in System.");
    activationLinkRepository.deleteAll(expiredLinks);
    log.info("Successfully cleaned up!");
  }
}

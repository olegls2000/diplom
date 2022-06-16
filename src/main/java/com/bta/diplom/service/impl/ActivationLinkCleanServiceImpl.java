package com.bta.diplom.service.impl;

import com.bta.diplom.repository.ActivationLinkRepository;
import com.bta.diplom.service.ActivationLinkCleanService;
import java.time.ZonedDateTime;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;


@Slf4j
@AllArgsConstructor
public class ActivationLinkCleanServiceImpl implements ActivationLinkCleanService {

  private ActivationLinkRepository activationLinkRepository;

  @Scheduled(initialDelay = 5_000l, fixedRate = 10_000l)
  @Transactional
  @Override
  public void clean() {
    final var checkDate = ZonedDateTime.now().minusMinutes(3l);
    final var expiredLinks = activationLinkRepository.findByCreatedBefore(checkDate);
    if (expiredLinks == null || expiredLinks.isEmpty()) {
      log.info("No expired Activation Links in System.");
      return;
    }
    log.info("Detected " + expiredLinks.size() + " Activation Links in System.");
    activationLinkRepository.deleteAll(expiredLinks);
    log.info("Successfully cleaned up!");
  }
}

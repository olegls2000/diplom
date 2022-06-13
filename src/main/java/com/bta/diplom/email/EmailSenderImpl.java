package com.bta.diplom.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderImpl implements EmailSender {

  @Autowired
  private JavaMailSender javaMailSender;

  @Override
  public void sendEmail(String to, String body, String title) {
    final SimpleMailMessage mailMessage = new SimpleMailMessage();
    //TODO prepare message ...
    //javaMailSender;
  }
}

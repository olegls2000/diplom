package com.bta.diplom.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

//@Configuration
public class EmailConfig {

  private static final String EMAIL_PASSWORD_ENV = "EMAIL_PASSWORD";

  //@Bean
  public JavaMailSender getJavaMailSender() {
    final var mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);
    mailSender.setUsername("pasechnik.o.n@gmail.com");
    mailSender.setPassword(System.getenv(EMAIL_PASSWORD_ENV));
    final var properties = mailSender.getJavaMailProperties();
    properties.setProperty("mail.transport.protocol", "smtp");
    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.smtp.starttls.enable", "true");

    return mailSender;
  }
}

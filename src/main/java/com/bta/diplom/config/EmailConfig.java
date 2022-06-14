package com.bta.diplom.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

  @Bean
  public JavaMailSender getJavaMailSender() {
    final var mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);
    mailSender.setUsername("pasechnik.o.n@gmail.com");
    mailSender.setPassword("?????");
    //TODO ....

    return mailSender;
  }
}

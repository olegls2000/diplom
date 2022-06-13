package com.bta.diplom.email;

public interface EmailSender {
  void sendEmail(String to, String body, String title);
}

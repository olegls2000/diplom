package com.bta.diplom.service;

import com.bta.diplom.dto.UserAccountDto;

public interface UserAccountService {

  void create(UserAccountDto userAccount);

   void activate(final String code);
}

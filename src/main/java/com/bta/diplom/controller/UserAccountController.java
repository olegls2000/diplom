package com.bta.diplom.controller;

import com.bta.diplom.dto.CustomerOrderDto;
import com.bta.diplom.dto.UserAccountDto;
import com.bta.diplom.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user-account")
@RestController
public class UserAccountController {

  @Autowired
  private UserAccountService userAccountService;

  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody UserAccountDto userAccount) {
    userAccountService.create(userAccount);
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping("/activate")
  public ResponseEntity<?> activate(@PathVariable String code){
    userAccountService.activate(code);
    return new ResponseEntity(HttpStatus.OK);
  }
}

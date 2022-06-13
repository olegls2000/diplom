package com.bta.diplom.controller;

import com.bta.diplom.dto.CustomerDto;
import com.bta.diplom.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping("/all")
  public List<CustomerDto> getAll() {
    return customerService.getAll();
  }

  @PostMapping("/create")
  public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customer) {
    customerService.create(customer);
    return new ResponseEntity<>(customer, HttpStatus.OK);
  }

}

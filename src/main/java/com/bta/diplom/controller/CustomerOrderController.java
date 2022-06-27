package com.bta.diplom.controller;


import com.bta.diplom.dto.CustomerOrderDto;
import com.bta.diplom.dto.CustomerOrdersDto;
import com.bta.diplom.service.CustomerOrderService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customer-order")
@RestController
public class CustomerOrderController {

  @Autowired
  private CustomerOrderService customerOrderService;

  @PostMapping("/create")
  public ResponseEntity<?> create(@Valid @RequestBody CustomerOrderDto customerOrder) {
    customerOrderService.create(customerOrder);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PostMapping("/create-all")
  public ResponseEntity<?> createAll(@RequestBody CustomerOrdersDto customerOrders) {
    customerOrderService.createAll(customerOrders);
    return new ResponseEntity(HttpStatus.OK);
  }

}

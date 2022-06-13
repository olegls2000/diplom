package com.bta.diplom.service;

import com.bta.diplom.dto.CustomerDto;
import com.bta.diplom.model.Customer;
import java.util.List;

public interface CustomerService {
  void create(CustomerDto customer);

  void update(CustomerDto customer);

  List<CustomerDto> getAll();
}

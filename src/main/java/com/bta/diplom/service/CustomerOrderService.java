package com.bta.diplom.service;

import com.bta.diplom.dto.CustomerOrderDto;
import com.bta.diplom.dto.CustomerOrdersDto;

public interface CustomerOrderService {
  void create(CustomerOrderDto customerOrder);
  void createAll(CustomerOrdersDto customerOrders);
}

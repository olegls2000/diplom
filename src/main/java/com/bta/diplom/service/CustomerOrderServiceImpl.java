package com.bta.diplom.service;

import com.bta.diplom.dto.CustomerOrderDto;
import com.bta.diplom.mapper.WebMapper;
import com.bta.diplom.model.CustomerOrder;
import com.bta.diplom.repository.CustomerOrderRepository;
import java.time.ZonedDateTime;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
  @Autowired
  private WebMapper<CustomerOrderDto, CustomerOrder> mapper;

  @Autowired
  private CustomerOrderRepository repository;

  @Transactional
  @Override
  public void create(CustomerOrderDto customerOrder) {
    customerOrder.setSubmissionDate(ZonedDateTime.now());
    final var orderToCreate = mapper.toEntity(customerOrder);
    repository.save(orderToCreate);
  }
}

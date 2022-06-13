package com.bta.diplom.service;

import com.bta.diplom.dto.CustomerDto;
import com.bta.diplom.mapper.WebMapper;
import com.bta.diplom.model.Customer;
import com.bta.diplom.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository repository;

  @Autowired
  private WebMapper<CustomerDto, Customer> mapper;

  @Override
  public void create(CustomerDto customer) {
    repository.save(mapper.toEntity(customer));
  }

  @Override
  public void update(CustomerDto customer) {
    create(customer);
  }

  @Override
  public List<CustomerDto> getAll() {
    return mapper.toDtos(repository.findAll());
  }
}

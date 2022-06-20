package com.bta.diplom.mapper;

import com.bta.diplom.dto.CustomerDto;
import com.bta.diplom.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerWebMapper
    implements WebMapper<CustomerDto, Customer> {

  @Override
  public CustomerDto toDto(Customer entity) {
    if (entity == null) {
      return null;
    }
    return CustomerDto.builder()
        .email(entity.getEmail())
        .lastName(entity.getLastName())
        .firstName(entity.getFirstName())
        .telephone(entity.getTelephone())
        .registrationCode(entity.getRegistrationCode())
        .build();
  }

  @Override
  public Customer toEntity(CustomerDto dto) {
    if (dto == null) {
      return null;
    }
    return Customer.builder()
        .firstName(dto.getFirstName())
        .email(dto.getEmail())
        .lastName(dto.getLastName())
        .registrationCode(dto.getRegistrationCode())
        .telephone(dto.getTelephone())
        .build();
  }
}

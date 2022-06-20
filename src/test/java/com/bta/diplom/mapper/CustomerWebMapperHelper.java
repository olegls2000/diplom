package com.bta.diplom.mapper;

import com.bta.diplom.model.Customer;
import java.math.BigInteger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerWebMapperHelper {

  static Customer getCustomer() {
    return Customer.builder()
        .telephone("+37277788899")
        .email("test@mail.com")
        .registrationCode(BigInteger.valueOf(300l))
        .firstName("FirstName")
        .lastName("LastName")
        .build();
  }
}

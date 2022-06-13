package com.bta.diplom.dto;

import java.math.BigInteger;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
  private String firstName;

  private String lastName;

  private BigInteger registrationCode;

  private String email;

  private String telephone;
}

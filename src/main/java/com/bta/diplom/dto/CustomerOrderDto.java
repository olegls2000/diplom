package com.bta.diplom.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderDto {

  @NotNull
  private CustomerDto customer;
  private String orderNumber;

  private List<OrderLineDto> orderLines;
}




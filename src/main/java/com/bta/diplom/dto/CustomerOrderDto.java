package com.bta.diplom.dto;

import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderDto {
  private String customerEmail;
  private String orderNumber;
  private ZonedDateTime submissionDate;

  private List<OrderLineDto> orderLines;
}

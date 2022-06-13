package com.bta.diplom.dto;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {
  private String username;
  private String password;
  private ZonedDateTime created;
  private String email;
}

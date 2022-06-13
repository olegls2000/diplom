package com.bta.diplom.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity

public class Product {
  @Id
  @GeneratedValue
  private Long id;

  @Size(max = 100)
  @NotBlank
  private String name;

  @NotNull
  private Integer skuCode;

  @NotNull
  @Min(value = 0)
  private Integer unitPrice;

  @ToString.Exclude
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
  private List<OrderLine> orderLines;

}

package com.bta.diplom.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "order_line")
public class OrderLine extends AbstractBaseEntity {

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  private int quantity;

  @ManyToOne
  @JoinColumn(name = "customer_order_id")
  private CustomerOrder customerOrder;
}

package com.bta.diplom.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.time.ZonedDateTime;
import javax.persistence.Column;
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
@Table(name = "customer_order")
public class CustomerOrder extends AbstractBaseEntity {

  @Column(name = "order_number")
  private String orderNumber;

  @ToString.Exclude
  @ManyToOne(cascade = ALL, fetch = EAGER)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  private ZonedDateTime submissionDate;
}

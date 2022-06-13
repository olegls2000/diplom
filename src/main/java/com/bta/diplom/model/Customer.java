package com.bta.diplom.model;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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

public class Customer extends AbstractBaseEntity {

  @Size(max = 20)
  @Column(name = "first_name")
  private String firstName;

  @Size(max = 20)
  @Column(name = "last_name")
  private String lastName;

  @Column(name = "registration_code")
  private BigInteger registrationCode;

  @Size(max = 50)
  private String email;

  @Size(max = 50)
  private String telephone;

  @ToString.Exclude
  @OneToMany(mappedBy = "customer",
      cascade = CascadeType.PERSIST,
      fetch = FetchType.LAZY)
  private List<CustomerOrder> customerOrders;
}


package com.bta.diplom.model;

import static javax.persistence.FetchType.EAGER;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activation_link")
public class ActivationLink extends AbstractBaseEntity {

  @Size(max = 36)
  @NotEmpty
  private String code;

  private ZonedDateTime created;

  @OneToOne(fetch = EAGER)
  @JoinColumn(name ="user_account_id" )
  private UserAccount userAccount;
}

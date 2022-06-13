package com.bta.diplom.model;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_account")
public class UserAccount extends AbstractBaseEntity {
  private String username;
  private String password;
  private boolean active;
  private ZonedDateTime created;
  private String email;

  @OneToOne(mappedBy = "userAccount", fetch = FetchType.LAZY)
  private ActivationLink activationLink;
}

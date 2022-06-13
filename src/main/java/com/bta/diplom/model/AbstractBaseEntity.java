package com.bta.diplom.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractBaseEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
}
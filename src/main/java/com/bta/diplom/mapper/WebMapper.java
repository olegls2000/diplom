package com.bta.diplom.mapper;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Collectors;

public interface WebMapper<D, E> {
  D toDto(E entity);

  E toEntity(D dto);

  default List<D> toDtos(List<E> entities) {
    if (entities == null) {
      return null;
    }
    return entities.stream()
        .map(entity -> toDto(entity))
        .collect(toList());
  }

  default List<E> toEntities(List<D> dtos) {
    if (dtos == null) {
      return null;
    }
    return dtos.stream()
        .map(dto -> toEntity(dto))
        .collect(toList());
  }
}

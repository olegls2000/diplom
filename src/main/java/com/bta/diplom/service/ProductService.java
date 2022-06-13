package com.bta.diplom.service;

import com.bta.diplom.dto.ProductDto;
import java.util.List;

public interface ProductService {
  List<ProductDto> getAll();

  ProductDto update(ProductDto product);

}

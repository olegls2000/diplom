package com.bta.diplom.service;

import com.bta.diplom.dto.ProductDto;
import com.bta.diplom.mapper.WebMapper;
import com.bta.diplom.model.Product;
import com.bta.diplom.repository.ProductRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository repository;

  @Autowired
  private EntityManager entityManager;

  @Autowired
  private WebMapper<ProductDto, Product> webMapper;

  @Override
  public List<ProductDto> getAll() {
    log.debug("Someone requests All Products. DEBUG!");
    log.info("Someone requests All Products. INFO!");
    log.warn("Someone requests All Products. WARN!");
    log.error("Someone requests All Products. ERROR!");
    return webMapper.toDtos(repository.findAll());
  }

  @Transactional
  @Override
  public ProductDto update(ProductDto product) {
    final Integer skuCode = product.getSkuCode();
    final Product productFromDb = repository.findBySkuCode(skuCode);
    if (productFromDb == null) {
      final String message = "Product with Sku Code = " + skuCode + "does not exist!";
      log.warn(message);
      throw new RuntimeException(message);
    }
    productFromDb.setUnitPrice(product.getUnitPrice());
    //entityManager.flush();

    return webMapper.toDto(productFromDb);
  }
}

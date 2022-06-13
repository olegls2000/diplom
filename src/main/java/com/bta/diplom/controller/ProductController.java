package com.bta.diplom.controller;


import com.bta.diplom.dto.ProductDto;
import com.bta.diplom.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/all")
  public List<ProductDto> getAll() {
    return productService.getAll();
  }

  @PutMapping("/update")
  public ResponseEntity<ProductDto> update(@RequestBody ProductDto product) {
    final ProductDto updatedProduct = productService.update(product);

    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
  }
}

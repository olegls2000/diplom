package com.bta.diplom.service.impl;

import com.bta.diplom.dto.CustomerOrderDto;
import com.bta.diplom.dto.CustomerOrdersDto;
import com.bta.diplom.dto.OrderLineDto;
import com.bta.diplom.dto.ProductDto;
import com.bta.diplom.mapper.WebMapper;
import com.bta.diplom.model.Customer;
import com.bta.diplom.model.CustomerOrder;
import com.bta.diplom.model.CustomerOrder.CustomerOrderBuilder;
import com.bta.diplom.model.OrderLine;
import com.bta.diplom.model.Product;
import com.bta.diplom.repository.CustomerOrderRepository;
import com.bta.diplom.repository.CustomerRepository;
import com.bta.diplom.repository.OrderLineRepository;
import com.bta.diplom.repository.ProductRepository;
import com.bta.diplom.service.CustomerOrderService;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
  @Autowired
  private WebMapper<CustomerOrderDto, CustomerOrder> mapper;

  @Autowired
  private CustomerOrderRepository repository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderLineRepository orderLineRepository;

  @Transactional
  @Override
  public void create(CustomerOrderDto customerOrder) {
    //customerOrder.setSubmissionDate(ZonedDateTime.now());
    final var orderToCreate = mapper.toEntity(customerOrder);
    repository.save(orderToCreate);
  }

  private static String generateOrderNumber() {
    return UUID.randomUUID().toString().substring(0,19);
  }

  private Customer enrichCustomer(final String email) {
    final Customer customer = customerRepository.findByEmail(email);
    if (customer == null) {
      throw new RuntimeException("Customer with email: " + email + " doesn't exist!");
    }
    return customer;
  }

  private CustomerOrder createCustomerOrder(final String customerEmail) {
    return repository.save(
        CustomerOrder.builder()
            .orderNumber(generateOrderNumber())
            .customer(enrichCustomer(customerEmail))
            .submissionDate(ZonedDateTime.now())
            .build());
  }

  private Product getOrCreateProduct(final Integer skuCode,
                                     final String productName,
                                     final Integer unitPrice) {
    Product product = productRepository.findBySkuCode(skuCode);
    if (product != null) {
      return product;
    }
    product = Product.builder()
        .skuCode(skuCode)
        .name(productName)
        .unitPrice(unitPrice)
        .build();
    return productRepository.save(product);
  }

  @Transactional
  @Override
  public void createAll(CustomerOrdersDto customerOrderDtos) {
    customerOrderDtos.getCustomerOrders()
        .forEach(orderDto -> {
          final CustomerOrder customerOrder =
              createCustomerOrder(orderDto.getCustomer().getEmail());

          orderDto.getOrderLines()
              .forEach(orderLineDto -> {
                final ProductDto productDto = orderLineDto.getProduct();
                orderLineRepository.save(
                    OrderLine.builder()
                        .quantity(orderLineDto.getQuantity())
                        .product(getOrCreateProduct(
                            productDto.getSkuCode(),
                            productDto.getName(),
                            productDto.getUnitPrice()))
                        .customerOrder(customerOrder)
                        .build());
              });
        });
  }
}

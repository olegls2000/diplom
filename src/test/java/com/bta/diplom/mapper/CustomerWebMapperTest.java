package com.bta.diplom.mapper;

import static com.bta.diplom.mapper.CustomerWebMapperHelper.getCustomer;
import static org.junit.jupiter.api.Assertions.*;

import com.bta.diplom.dto.CustomerDto;
import com.bta.diplom.model.Customer;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class CustomerWebMapperTest {

  private final CustomerWebMapper instanceUnderTest = new CustomerWebMapper();

  @Test
  public void testMapNullEntityToDto() {
    //given
    final Customer entity = null;

    //when
    final CustomerDto actualResult = instanceUnderTest.toDto(entity);

    //then
    assertEquals(null, actualResult);
  }

  @Test
  public void testMapNotNullEntityToDto() {
    //given
    final Customer customer = getCustomer();
    //when
    final CustomerDto actualResult = instanceUnderTest.toDto(customer);

    //then
    assertEquals("+37277788899", actualResult.getTelephone());
    assertEquals("test@mail.com", actualResult.getEmail());
    assertEquals("FirstName", actualResult.getFirstName());
    assertEquals("LastName", actualResult.getLastName());
    assertEquals(BigInteger.valueOf(300l), actualResult.getRegistrationCode());
  }

  @Test
  public void testMapNullCollectionEntityToDtos() {
    //given
    final List<Customer> customers = null;

    //when
    final List<CustomerDto> actualResults = instanceUnderTest.toDtos(customers);

    //then
    assertEquals(null, actualResults);
  }

  @Test
  public void testMapCollectionEntityToDtos() {
    //given
    final List<Customer> customers = Collections.singletonList(getCustomer());

    //when
    final List<CustomerDto> actualResults =
        instanceUnderTest.toDtos(customers);

    //then
    assertEquals(1, actualResults.size());
  }


}
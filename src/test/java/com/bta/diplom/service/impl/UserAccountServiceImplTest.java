package com.bta.diplom.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bta.diplom.model.ActivationLink;
import com.bta.diplom.model.UserAccount;
import com.bta.diplom.repository.ActivationLinkRepository;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceImplTest {

  @Mock
  private ActivationLinkRepository activationLinkRepository;

  @InjectMocks
  private UserAccountServiceImpl instanceUnderTest = new UserAccountServiceImpl();

  @Test
  public void testNullCode() {
    //given
    final String code = null;

    //when
    final RuntimeException actualResult =
        assertThrows(RuntimeException.class, () -> instanceUnderTest.activate(code));

    //then
    assertEquals("Activation code must not be null or Empty!", actualResult.getMessage());

  }

  @Test
  public void testEmptyCode() {
    //given
    final String code = "";

    //when
    final RuntimeException actualResult =
        assertThrows(RuntimeException.class, () -> instanceUnderTest.activate(code));

    //then
    assertEquals("Activation code must not be null or Empty!", actualResult.getMessage());

  }

  @Test
  public void testNotNullInvalidCode() {
    //given
    final String code = "TEST_CODE";
    final ActivationLink activationLink = null;

    when(activationLinkRepository.findByCode(code))
        .thenReturn(activationLink);

    //when
    final RuntimeException actualResult =
        assertThrows(RuntimeException.class, () -> instanceUnderTest.activate(code));

    //then
    assertEquals("Invalid code in activation link: TEST_CODE",
        actualResult.getMessage());
  }

  @Test
  public void testNotNullExpiredCode() {
    //given
    final String code = "TEST_CODE";
    final ActivationLink activationLink = ActivationLink.builder()
        .code(code)
        .created(ZonedDateTime.now().minusDays(1))
        .build();

    when(activationLinkRepository.findByCode(code))
        .thenReturn(activationLink);

    //when
    final RuntimeException actualResult =
        assertThrows(RuntimeException.class, () -> instanceUnderTest.activate(code));

    //then
    assertEquals("Activation link with code: TEST_CODE already expired",
        actualResult.getMessage());
  }

  @Test
  public void testNotNullNotExpiredCode() {
    //given
    final String code = "TEST_CODE";

    final UserAccount userAccount = UserAccount.builder()
        .active(false)
        .build();

    final ActivationLink activationLink = ActivationLink.builder()
        .code(code)
        .created(ZonedDateTime.now())
        .userAccount(userAccount)
        .build();

    when(activationLinkRepository.findByCode(code))
        .thenReturn(activationLink);

    //when
    instanceUnderTest.activate(code);

    //then
    assertEquals(true, activationLink.getUserAccount().isActive());
    assertTrue(activationLink.getUserAccount().isActive());

    verify(activationLinkRepository).delete(any());
    //Strict
    verify(activationLinkRepository, times(1)).delete(activationLink);
  }

}
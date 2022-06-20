package com.bta.diplom.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bta.diplom.model.ActivationLink;
import com.bta.diplom.repository.ActivationLinkRepository;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActivationLinkCleanServiceImplTest {

  private ActivationLinkRepository activationLinkRepository;

  private ActivationLinkCleanServiceImpl instanceUnderTest;

  @BeforeEach
  void beforeEach() {
    activationLinkRepository = mock(ActivationLinkRepository.class);
    instanceUnderTest = new ActivationLinkCleanServiceImpl(activationLinkRepository);
  }

  @Test
  public void testIfNoExpiredActivationLinks() {
    //given
    when(activationLinkRepository.findByCreatedBefore(any())).thenReturn(Collections.emptyList());

    //when
    instanceUnderTest.clean();

    //then
    verify(activationLinkRepository, never()).deleteAll(any());
  }

  @Test
  public void testIfExpiredActivationLinks() {
    //given
    when(activationLinkRepository.findByCreatedBefore(any()))
        .thenReturn(Collections.singletonList(
            ActivationLink.builder().build())
        );

    //when
    instanceUnderTest.clean();

    //then
    verify(activationLinkRepository, times(1)).deleteAll(any());
  }

  @Test
  public void testIfExpiredActivationLinksAreNull() {
    //given
    when(activationLinkRepository.findByCreatedBefore(any()))
        .thenReturn(null);

    //when
    instanceUnderTest.clean();

    //then
    verify(activationLinkRepository, times(0)).deleteAll(any());
  }


}
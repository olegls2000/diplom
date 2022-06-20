package com.bta.diplom.controller;

import com.bta.diplom.exception.ResolvingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvise {

  @ExceptionHandler(value = {
      ConstraintViolationException.class,
      ResolvingException.class,
      RuntimeException.class
  })
  public ResponseEntity<Object> handleStatus400(HttpServletRequest request,
                                                final ConstraintViolationException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }
}

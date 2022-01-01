package com.leapwise.test.task.handlers;

import java.util.NoSuchElementException;

import com.leapwise.test.task.controllers.FrequencyController;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = FrequencyController.class)
public class FrequencyRestExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(value 
    = { NoSuchElementException.class})
  protected ResponseEntity<Object> handleConflict(
    RuntimeException ex, WebRequest request) {
      return handleExceptionInternal(ex, "No Topics Found To Display", 
        new HttpHeaders(), HttpStatus.CONFLICT, request);
  }
}

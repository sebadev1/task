package com.leapwise.test.task.handlers;

import com.leapwise.test.task.controllers.AnalyseController;
import com.leapwise.test.task.exceptions.WrongParameterSizeException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = AnalyseController.class)
public class AnalysisRestExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(value 
    = { WrongParameterSizeException.class})
  protected ResponseEntity<Object> handleConflict(
    RuntimeException ex, WebRequest request) {
      return handleExceptionInternal(ex, "Please check provided data", 
        new HttpHeaders(), HttpStatus.CONFLICT, request);
  }
}

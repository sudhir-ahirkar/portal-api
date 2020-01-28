package com.portal.exception.advice;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portal.exception.UserCreateFailedException;
import com.portal.exception.dto.DataValidationMsgResponse;
import com.portal.exception.dto.ServiceErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

  
  @ExceptionHandler(UserCreateFailedException.class)
  public ResponseEntity<ServiceErrorResponse> handleNullEntityException(WebRequest request, UserCreateFailedException e)
      throws JsonProcessingException {

  LOGGER.error("UserCreationException().." + e.getMessage(), e);
  String errorCode = "";
  ServiceErrorResponse errorResponse=new ServiceErrorResponse();
 
  DataValidationMsgResponse dataValidationMsgResponse=new DataValidationMsgResponse("","","User is already exist","username");
  List<DataValidationMsgResponse> validationMsgResponses=new ArrayList<>();
  validationMsgResponses.add(dataValidationMsgResponse);
  errorResponse.setErrors(validationMsgResponses);
//  LOGGER.error(DataValidationMsgResponse.writeValueAsString(DataValidationMsgResponse));
  return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}

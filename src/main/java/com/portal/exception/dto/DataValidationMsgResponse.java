package com.portal.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataValidationMsgResponse {
  private String msgCategory;
  private String msgCode;
  private String errorMessage;
  private String field;
}

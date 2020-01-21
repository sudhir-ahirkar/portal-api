package com.portal.dto.in;


import com.portal.util.ObjectMapperUtils;

import lombok.Data;

@Data
public class RefDataDTO {

  private Long id;
  
  private String identifier;
  
  private String value;
  
  private Boolean isActive;
  
  public static RefDataDTO toRefData(Object country) {
     RefDataDTO refDataDTO= ObjectMapperUtils.map(country, RefDataDTO.class);
     return refDataDTO;
    
  }
  
}

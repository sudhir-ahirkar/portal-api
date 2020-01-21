package com.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.dto.in.UserRefDataDTO;
import com.portal.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/ref-data")
@RestController
public class RefDataController {
  
  @Autowired
  private UserService userService;
  
  @GetMapping(value="/user")
  public ResponseEntity<UserRefDataDTO> getTimeTableRefData() {
    UserRefDataDTO userRefDataDTO = userService.getRefData();
    return new ResponseEntity<UserRefDataDTO>(userRefDataDTO, HttpStatus.OK);
  }
}

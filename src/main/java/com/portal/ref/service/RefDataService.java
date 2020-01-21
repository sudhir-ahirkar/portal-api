package com.portal.ref.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.model.ref.Country;
import com.portal.ref.repository.CountryRepository;

@Service
public class RefDataService {

  @Autowired
  private CountryRepository countryRepository;
  
  
  public List<Country> getCountry() {
    return countryRepository.findAllByIsActive(Boolean.TRUE);
  }

}

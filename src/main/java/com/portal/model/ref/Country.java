package com.portal.model.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ref_country")
public class Country {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  
//  @Column
//  private String description;
  
  @Column(name="name")
  private String value;
  
  @Column
  private String identifier;
  
  @Column(name="is_active")
  private Boolean isActive;
  
//  @Column(name="is_deleted")
//  private Long isDeleted;
  

}

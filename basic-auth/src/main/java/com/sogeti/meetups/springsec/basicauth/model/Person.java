package com.sogeti.meetups.springsec.basicauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
  private String firstName;
  private String lastName;
  private String email;
}
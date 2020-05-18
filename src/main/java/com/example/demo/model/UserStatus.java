package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserStatus {
  ONLINE, AWAY, OFFLINE;


  @JsonCreator
  public static UserStatus getStatus(@JsonProperty("userStatus") String status){
    return UserStatus.valueOf(status.toUpperCase());
  }
}

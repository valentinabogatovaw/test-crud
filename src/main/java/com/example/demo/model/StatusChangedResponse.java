package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StatusChangedResponse {

  private Long ID;
  private UserStatus currentStatus;
  private UserStatus lastStatus;

}

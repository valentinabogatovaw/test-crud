package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
  String timestamp;
  int status;
  String details;
  String path;
}

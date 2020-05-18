package com.example.demo.endpoint;

import com.example.demo.exceptions.ServiceException;
import com.example.demo.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdviser {
  @ExceptionHandler(ServiceException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public Map<String, Object> sendResponse(ServiceException e, final HttpServletRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", 400);
    body.put("details", e.getMessage());
    body.put("path", request.getRequestURI());
    return body;
  }

  @ExceptionHandler({HttpMessageNotReadableException.class, IllegalArgumentException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public Map<String, Object> sendResponse(Exception e, final HttpServletRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", 400);
    body.put("path", request.getRequestURI());
   if (e.getMessage().contains("Cannot construct instance of `com.example.demo.model.UserStatus")) {
     body.put("details", "Status can be only ONLINE, AWAY, OFFLINE");
   }else {
     body.put("details", e.getMessage());
   }

   return body;
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public Map<String, Object> sendResponse(UserNotFoundException e, final HttpServletRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", 404);
    body.put("details", e.getMessage());
    body.put("path", request.getRequestURI());
    return body;
  }


}


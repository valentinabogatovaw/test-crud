package com.example.demo.endpoint;

import com.example.demo.TimeUtils;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.ErrorDto;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdviser {
  @ExceptionHandler(ServiceException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorDto sendResponse(ServiceException e, final HttpServletRequest request) {
    return ErrorDto.builder().timestamp(TimeUtils.getTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
        .details(e.getMessage())
        .status(400)
        .path(request.getRequestURI()).build();
  }

  @ExceptionHandler({HttpMessageNotReadableException.class, IllegalArgumentException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorDto sendResponse(Exception e, final HttpServletRequest request) {
    String message;
    if (e.getMessage().contains("Cannot construct instance of `com.example.demo.model.UserStatus")) {
      message = "Status can be only ONLINE, AWAY, OFFLINE";
    }else {
      message = e.getMessage();
    }
    return ErrorDto.builder().timestamp(TimeUtils.getTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
        .status(400)
        .details(message)
        .path(request.getRequestURI()).build();
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorDto sendResponse(UserNotFoundException e, final HttpServletRequest request) {
    return ErrorDto.builder().timestamp(TimeUtils.getTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                   .details(e.getMessage())
                   .status(404)
                   .path(request.getRequestURI()).build();
  }


}


package com.example.demo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtils {
  public static LocalDateTime getTime(){
    Instant instant = Instant.now();
    ZoneId z = ZoneId.of( "Europe/Moscow" );
    ZonedDateTime zdt = instant.atZone( z );
    return zdt.toLocalDateTime();
  }

}

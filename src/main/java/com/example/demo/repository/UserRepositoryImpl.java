package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.UserStatus;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

  @PersistenceContext
  EntityManager entityManager;

  @Scheduled(cron = "0 * * * * *")
  @Transactional
  @Override
  public void updateStatuseOffline() {
    Query query = entityManager.createNativeQuery(
        "update users set user_status = :userStatus where users.last_update < :datePlFive",
        User.class);
    query.setParameter("userStatus", UserStatus.AWAY.toString());
    query.setParameter("datePlFive", getTime().minusMinutes(5));
    query.executeUpdate();
  }

  private LocalDateTime getTime(){
    Instant instant = Instant.now();
    ZoneId z = ZoneId.of( "Europe/Moscow" );
    ZonedDateTime zdt = instant.atZone( z );
    return zdt.toLocalDateTime();
  }
}

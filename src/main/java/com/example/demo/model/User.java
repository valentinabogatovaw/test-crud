package com.example.demo.model;

import com.example.demo.validation.FieldsConstraint;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;


@Data
@Entity
@Table(name = "users")
@FieldsConstraint
public class User {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long ID;

  private String email;


  @Enumerated(EnumType.STRING)
  private UserStatus userStatus;
  private String name;

  private String phoneNumber;

  private LocalDateTime lastUpdate;

}

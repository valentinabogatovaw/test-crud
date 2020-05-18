package com.example.demo.validation;

import com.example.demo.model.User;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = FieldsValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsConstraint {
  String message() default "{RequestAnnotation}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

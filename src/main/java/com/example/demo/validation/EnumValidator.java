package com.example.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValidation, Object> {

  private String[] values;

  @Override
  public void initialize(EnumValidation constraintAnnotation) {
    this.values = constraintAnnotation.values();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    {
      if (null != value) {
        String contextValue = value.toString();
        for (Object enumValue : values) {
          if (enumValue.toString()
                       .equals(contextValue)) {
            return true;
          }
        }
      }

      return false;
    }
  }
}

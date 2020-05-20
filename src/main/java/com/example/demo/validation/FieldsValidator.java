package com.example.demo.validation;

import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsValidator implements ConstraintValidator<FieldsConstraint, User> {


  @Override
  public void initialize(FieldsConstraint constraintAnnotation) {

  }

  @Override
  public boolean isValid(User user, ConstraintValidatorContext context) {
    Pattern pattern = Pattern.compile("^.+@.+\\..+$");
    Matcher matcher = pattern.matcher(user.getEmail());
    Pattern pattern1 = Pattern.compile("^[0-9]+$");
    Matcher matcher2 = pattern1.matcher(user.getPhoneNumber());
    if (matcher.matches() && matcher2.matches()) return true;
    if (!matcher.matches() && matcher2.matches()) throw new ServiceException("Invalid email. Example of email: email@gmail.com");
    if (matcher.matches() && !matcher2.matches()) throw new ServiceException("Invalid phone number");
    if (!matcher.matches() && !matcher2.matches()) throw new ServiceException("Invalid phone number and email");
    return false;
  }
}



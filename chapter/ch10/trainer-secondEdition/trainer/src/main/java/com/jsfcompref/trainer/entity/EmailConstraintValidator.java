package com.jsfcompref.trainer.entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailConstraintValidator
      implements ConstraintValidator<Email, String> {

    public void initialize(Email parameters) {
    }

    public boolean isValid(String value, ConstraintValidatorContext ctxt) {

        boolean result = true;
        if (-1 == value.indexOf("@")) {
            result = false;
        }

        return result;
    }

}

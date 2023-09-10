package com.jsfcompref;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyConstraintValidator
      implements ConstraintValidator<NotEmpty, Object> {

    public void initialize(NotEmpty parameters) {
    }

    public boolean isValid(Object value, ConstraintValidatorContext ctxt) {

        boolean result = (null != value && 0 < value.toString().length())
                ? true : false;

        return result;
    }

}

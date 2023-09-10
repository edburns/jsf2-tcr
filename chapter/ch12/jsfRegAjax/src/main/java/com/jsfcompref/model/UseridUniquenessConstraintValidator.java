package com.jsfcompref.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UseridUniquenessConstraintValidator implements
        ConstraintValidator<UseridUniquenessConstraint, String> {

    public boolean isValid(String value, ConstraintValidatorContext ctx) {


        boolean result = (null != value && 3 < value.length());

        return result;
    }

    public void initialize(UseridUniquenessConstraint arg0) {
    }
    
}

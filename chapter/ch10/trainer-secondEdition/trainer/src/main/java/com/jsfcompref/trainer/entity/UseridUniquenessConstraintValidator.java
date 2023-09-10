package com.jsfcompref.trainer.entity;

import com.jsfcompref.trainer.entity.accessor.UserRegistry;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UseridUniquenessConstraintValidator implements
        ConstraintValidator<UseridUniquenessConstraint, String> {

    public boolean isValid(String value, ConstraintValidatorContext ctx) {


        UserRegistry registry = UserRegistry.getCurrentInstance();
        boolean result = true;

        if (null != registry) {
            result = (null != registry.getUserByUserid(value));
        }
        return result;
    }

    public void initialize(UseridUniquenessConstraint arg0) {
    }
    
}

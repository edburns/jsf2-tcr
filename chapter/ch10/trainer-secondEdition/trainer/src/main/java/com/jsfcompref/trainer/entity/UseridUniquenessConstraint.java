package com.jsfcompref.trainer.entity;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintPayload;

@Documented
@Constraint(validatedBy = UseridUniquenessConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UseridUniquenessConstraint {
    
	String message() default "A user with that userid already exists";

	Class<?>[] groups() default {};

    Class<? extends ConstraintPayload>[] payload() default {};
    

}

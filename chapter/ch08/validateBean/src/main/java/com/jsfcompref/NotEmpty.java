
package com.jsfcompref;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintPayload;

@Documented
@Constraint(validatedBy = NotEmptyConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {

    String message() default "{validator.notEmpty}";

    Class<? extends ConstraintPayload>[] payload() default {};

    Class<?>[] groups() default {};
}

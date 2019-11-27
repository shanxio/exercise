package com.nf.spring.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;



/**
 * @author Sam
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { PhoneValidator.class})
public @interface Phone {
    String message() default "{手机号码验证错误}";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

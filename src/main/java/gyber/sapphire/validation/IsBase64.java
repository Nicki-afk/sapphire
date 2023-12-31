package gyber.sapphire.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import gyber.sapphire.validation.validators.IsBase64Validator;

@Target({ElementType.FIELD , ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsBase64Validator.class)
public @interface IsBase64 {

    String message() default "The string is not a valid Base64 string";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}

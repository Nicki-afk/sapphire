package gyber.sapphire.validation.validators;

import java.util.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import gyber.sapphire.validation.annotations.IsBase64;

public class IsBase64Validator implements ConstraintValidator<IsBase64 , String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        

        try { Base64.getDecoder().decode(value); return true;} catch (Exception e) {return false;}

    }
    
}

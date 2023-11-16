package gyber.sapphire.validation.validators;

import java.util.Base64;


import gyber.sapphire.validation.IsBase64;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsBase64Validator implements ConstraintValidator<IsBase64 , String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        

        try { Base64.getDecoder().decode(value); return true;} catch (Exception e) {return false;}

    }
    
}

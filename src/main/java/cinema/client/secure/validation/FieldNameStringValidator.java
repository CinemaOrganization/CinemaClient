package cinema.client.secure.validation;

import cinema.client.secure.validation.annotation.ValidName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldNameStringValidator implements ConstraintValidator<ValidName,String> {

    @Override
    public void initialize(ValidName validName) {

    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if (name != null){
            name = name.trim();
            return !name.isEmpty();
        }
        return false;
    }
}

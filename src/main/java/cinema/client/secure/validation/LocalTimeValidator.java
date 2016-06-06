package cinema.client.secure.validation;


import cinema.client.secure.validation.annotation.NotEmptyLocalTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;

public class LocalTimeValidator implements ConstraintValidator<NotEmptyLocalTime,LocalTime> {
    @Override
    public void initialize(NotEmptyLocalTime validLocalTime) {
    }

    @Override
    public boolean isValid(LocalTime localTime, ConstraintValidatorContext constraintValidatorContext) {

        if (localTime != null){
            return true;
        }
        return false;
    }
}

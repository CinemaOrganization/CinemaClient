package cinema.client.secure.validation.annotation;

import cinema.client.secure.validation.FieldNameStringValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldNameStringValidator.class)
public @interface ValidName {
    String message() default "{validation.annotation.ValidName}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

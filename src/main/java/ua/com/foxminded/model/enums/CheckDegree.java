package ua.com.foxminded.model.enums;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckDegreeValidator.class)
@Documented
public @interface CheckDegree {

    String message() default "{ua.com.foxminded.constraints.CheckDegree}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String value();
    
}

package ua.com.foxminded.model.enums;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckDegreeValidator implements ConstraintValidator<CheckDegree, String> {

    private Degree degree;

//    public void initialize(CheckDegree constraintAnnotation) {
//        this.degree = constraintAnnotation.value();
//    }

    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {

        if (object == null)
            return true;

        boolean isValid = Optional.ofNullable(Degree.valueOf(object)).isPresent();

        if (!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext
                             .buildConstraintViolationWithTemplate("{ua.com.foxminded.constraints.CheckDegree.message}")
                             .addNode("myProperty")
                             .addConstraintViolation();
        }
        return isValid;
    }
}

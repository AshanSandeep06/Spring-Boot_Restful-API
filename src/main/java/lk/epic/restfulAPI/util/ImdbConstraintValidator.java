package lk.epic.restfulAPI.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImdbConstraintValidator implements ConstraintValidator<ValidImdb, String> {
    @Override
    public boolean isValid(String imdb, ConstraintValidatorContext constraintValidatorContext) {
        if (imdb == null || imdb.equals("")) {
            return false;
        }

        return imdb.matches("^(tt)[0-9]{7}$");
    }
}

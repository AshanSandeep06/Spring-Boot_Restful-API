package lk.epic.restfulAPI.util;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImdbConstraintValidator.class)
public @interface ValidImdb {
    String message() default "Imdb should be in this pattern eg:-tt0371743, tt0371769";
}

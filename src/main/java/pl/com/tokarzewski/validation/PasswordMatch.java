package pl.com.tokarzewski.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PasswordMatch {
    String message() default "{pl.com.tokarzewski.validation.PasswordMatchValidator.message}";
    Class<?>[] groups() default{};
    public  abstract Class<? extends Payload>[] payload() default {};
}

package pl.com.tokarzewski.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.tokarzewski.api.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String>{

    private UserService userService;

    @Override
    public void initialize(UniqueEmail uniqueEmail) {
    }


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByEmail(s) == null;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

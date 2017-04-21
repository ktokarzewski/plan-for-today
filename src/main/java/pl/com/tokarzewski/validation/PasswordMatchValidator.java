package pl.com.tokarzewski.validation;

import pl.com.tokarzewski.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch,UserDto> {
    @Override
    public void initialize(PasswordMatch passwordMatch) {

    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getPassword() != null && userDto.getPassword().equals(userDto.getPasswordConfirmation());
    }
}

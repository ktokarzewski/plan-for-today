package pl.com.tokarzewski.dto;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import pl.com.tokarzewski.validation.PasswordMatch;
import pl.com.tokarzewski.validation.UniqueEmail;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
@PasswordMatch
public class UserDto implements Serializable{
    private long id;
    @Email
    @NotEmpty
    @UniqueEmail
    private String email;

    @Size(min = 8, message = "{pl.com.tokarzewski.validation.passwordConstraints.message}")
    private String password;
    private String passwordConfirmation;
    private String firstName;
    private String lastName;

    public long getId() {
        return id;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

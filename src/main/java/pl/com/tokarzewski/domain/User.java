package pl.com.tokarzewski.domain;

import pl.com.tokarzewski.api.AbstractDomainObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class User extends AbstractDomainObject {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @Transient
    private String password;
    @Column(nullable = false)
    private String encryptedPassword;

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
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
}

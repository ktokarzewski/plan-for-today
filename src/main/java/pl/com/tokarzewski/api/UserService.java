package pl.com.tokarzewski.api;

import pl.com.tokarzewski.authentication.EmailExistException;
import pl.com.tokarzewski.domain.User;

import java.util.Collection;

public interface UserService extends CRUDService<User>{
    User findByEmail(String s);

    void createUserAccount(User user) throws EmailExistException;

}

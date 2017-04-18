package pl.com.tokarzewski.api;

import pl.com.tokarzewski.authentication.EmailExistException;
import pl.com.tokarzewski.domain.User;

import java.util.Collection;

public interface UserService {
    User findByEmail(String s);

    void createUserAccount(User user) throws EmailExistException;

    void create(User user);

    void updateUser(User user);

    Collection<User> findAll();

    User findById(long id);

    void deleteUser(User user);
}

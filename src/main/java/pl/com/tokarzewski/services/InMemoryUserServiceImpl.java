package pl.com.tokarzewski.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.com.tokarzewski.api.UserService;
import pl.com.tokarzewski.domain.User;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Profile("!database")
public class InMemoryUserServiceImpl implements UserService {
    private Collection<User> users = new ArrayList<>();

    @Override
    public User findByEmail(String email) {
        for (User user :
                users) {
            if (email.equals(user.getEmail()))
                return user;
        }
        return null;
    }

    @Override
    public void createUserAccount(User user) {
        users.add(user);
    }

    @Override
    public void create(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateUser(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<User> findAll() {
        return users;
    }

    @Override
    public User findById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUser(User user) {
        throw new UnsupportedOperationException();
    }
}

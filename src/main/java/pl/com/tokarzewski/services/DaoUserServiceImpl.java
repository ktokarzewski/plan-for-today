package pl.com.tokarzewski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.com.tokarzewski.api.RoleService;
import pl.com.tokarzewski.api.ScoreService;
import pl.com.tokarzewski.api.TaskService;
import pl.com.tokarzewski.api.UserService;
import pl.com.tokarzewski.authentication.EmailExistException;
import pl.com.tokarzewski.authentication.EncryptionService;
import pl.com.tokarzewski.dao.UserRepository;
import pl.com.tokarzewski.domain.Role;
import pl.com.tokarzewski.domain.Task;
import pl.com.tokarzewski.domain.User;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Profile("database")
@Service
public class DaoUserServiceImpl implements UserService {

    private UserRepository userRepository;
    private EncryptionService encryptionService;
    private RoleService roleService;
    private TaskService taskService;
    private ScoreService scoreService;

    @Override
    public User findByEmail(String s) {
        return userRepository.findByEmail(s);
    }

    @Transactional
    @Override
    public void createUserAccount(User user) throws EmailExistException {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            Role userRole = roleService.findByName("ROLE_USER");
            user.getRoles().add(userRole);
            create(user);
        } else {
            throw new EmailExistException();
        }
    }


    @Override
    public void create(User user) {
        if (user.getPassword() != null) {
            user.setEncryptedPassword(encryptionService.encrypt(user.getPassword()));
            user.setPassword(null);
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        User u = findById(user.getId());
        if (user.getEmail() == null) {
            user.setEmail(u.getEmail());
        }

        if (user.getRoles().isEmpty()) {
            user.setRoles(u.getRoles());
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setEncryptedPassword(u.getEncryptedPassword());
            user.setPassword(null);
        }

        if (user.getFirstName() == null) {
            user.setFirstName(u.getFirstName());
        }

        if (user.getLastName() == null) {
            user.setLastName(u.getLastName());
        }

        create(user);
    }

    @Transactional
    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findOne(id);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        scoreService.deleteUserScore(user);
        taskService.deleteAllUserTasks(user);
        userRepository.delete(user);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
    @Autowired
    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }
}

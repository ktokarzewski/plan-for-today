package pl.com.tokarzewski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.tokarzewski.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

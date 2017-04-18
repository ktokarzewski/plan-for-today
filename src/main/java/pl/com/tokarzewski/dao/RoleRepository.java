package pl.com.tokarzewski.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.tokarzewski.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);
}

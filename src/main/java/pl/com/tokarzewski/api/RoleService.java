package pl.com.tokarzewski.api;


import pl.com.tokarzewski.domain.Role;

public interface RoleService {
    Role findByName(String name);

    void save(Role role);

    Role createIfNotExist(String roleName);

    Role createRole(String name);
}

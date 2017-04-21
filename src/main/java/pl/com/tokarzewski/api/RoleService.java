package pl.com.tokarzewski.api;


import pl.com.tokarzewski.domain.Role;

public interface RoleService{
    Role findByName(String name);

    Role getUserRole();

    Role getAdminRole();

    Role create(Role role);

    Role createIfNotExist(String roleName);

    Role createRole(String name);
}

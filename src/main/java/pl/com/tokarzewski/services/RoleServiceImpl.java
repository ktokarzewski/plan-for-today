package pl.com.tokarzewski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.com.tokarzewski.api.RoleService;
import pl.com.tokarzewski.dao.RoleRepository;
import pl.com.tokarzewski.domain.Role;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role getUserRole() {
        return roleRepository.findByName("USER_ROLE");
    }

    @Override
    public Role getAdminRole() {
        return roleRepository.findByName("ADMIN_ROLE");
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }


    @Override
    public Role createIfNotExist(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = createRole(roleName);
            roleRepository.save(role);
        }
        return role;
    }

    @Override
    public Role createRole(String name) {
        Role role = new Role();
        role.setName(name);
        return role;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}

package com.telros.usertask.configuration;

import com.telros.usertask.entity.Role;
import com.telros.usertask.entity.User;
import com.telros.usertask.exception.TaskException;
import com.telros.usertask.repository.RoleRepository;
import com.telros.usertask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminConfiguration {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminConfiguration(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    public User AdminSetUp() throws TaskException {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@admin.com");
        admin.setRole(roleRepository.findByRoleType(Role.RoleType.ROLE_ADMIN).orElseGet(()-> {
            Role roleAdmin = new Role(1, Role.RoleType.ROLE_ADMIN);
            return roleRepository.save(roleAdmin);
        }));
        userService.saveAdminDefault(admin);
        return admin;

    }




}

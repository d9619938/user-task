package com.telros.usertask.repository;

import com.telros.usertask.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    boolean existsByRoleType(Role.RoleType roleType);
    Optional<Role> findByRoleType(Role.RoleType roleType);

}

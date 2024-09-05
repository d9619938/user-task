package com.telros.usertask.repository;

import com.telros.usertask.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findUserById(Long id);
    Optional<List<User>> findUserByFirstName(String firstName);
    Optional<List<User>> findUserByLastName(String lastName);
    Optional<List<User>> findUserByMiddleName(String middleName);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(Long phoneNumber);




}

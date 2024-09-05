package com.telros.usertask.service;

import com.telros.usertask.entity.Role;
import com.telros.usertask.entity.User;
import com.telros.usertask.exception.TaskException;
import com.telros.usertask.repository.RoleRepository;
import com.telros.usertask.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }
    public Long createdUser(User user) throws TaskException {
        if (userRepository.findByEmail(user.getEmail()).isPresent() ||
        userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new TaskException("contact information is not unique");
        }
        return userRepository.save(user).getId();
    }

    public List<User> getAllUsers() {
        List<User> userList = (List<User>) userRepository.findAll();
        if (userList.isEmpty()) throw new TaskException("userList is empty");
        return userList;
    }
    public User getUserById(Long id) {
        if (!userRepository.existsById(id)) throw new TaskException("user " + id + " not found");
        return userRepository.findById(id).get();
    }
    public List<User> getUserByFistName(String fistName) {
        Optional<List<User>> userList = userRepository.findUserByFirstName(fistName);
        if (userList.isEmpty()) throw new TaskException("user with name " + fistName + " not found");
        return userList.get();
    }
    public User updateUser(User user) throws TaskException {
        if (!userRepository.existsById(user.getId())) throw new TaskException("user with " + user.getId() +" not found");
        return userRepository.save(user);
    }
    public void deleteUser(Long id) throws TaskException {
        if (!userRepository.existsById(id)) throw new TaskException("user with " + id +" not found");
        userRepository.deleteById(id);
    }

    public void saveAdminDefault(User admin) throws TaskException {
            if(userRepository.existsByUsername(admin.getUsername())) {
                throw new TaskException("Пользователь с именем " + admin.getUsername() + " уже существует");
            }
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            userRepository.save(admin);

    }

    public void saveUser(@Valid User user)throws TaskException {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new TaskException("Пользователь с именем " + user.getUsername() + " уже существует");
        }
        user.setRole(roleRepository.findByRoleType(Role.RoleType.ROLE_USER).orElseThrow(()-> new TaskException("ошибка в роли")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}

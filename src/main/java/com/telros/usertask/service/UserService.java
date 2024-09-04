package com.telros.usertask.service;

import com.telros.usertask.entity.User;
import com.telros.usertask.exception.TaskException;
import com.telros.usertask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}

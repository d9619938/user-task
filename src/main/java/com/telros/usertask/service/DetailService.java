package com.telros.usertask.service;

import com.telros.usertask.entity.UserDetail;
import com.telros.usertask.entity.User;
import com.telros.usertask.exception.TaskException;
import com.telros.usertask.repository.UserDetailRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailService {
    private final UserDetailRepository userDetailRepository;

    @Autowired
    public DetailService(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    public UserDetail getDetail(long id) {
        if (!userDetailRepository.existsById(id)) throw new TaskException("user " + id + " not found");
        return userDetailRepository.findById(id);
    }
}

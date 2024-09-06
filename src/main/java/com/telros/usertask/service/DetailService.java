package com.telros.usertask.service;

import com.telros.usertask.entity.UserDetail;
import com.telros.usertask.entity.User;
import com.telros.usertask.exception.TaskException;
import com.telros.usertask.repository.UserDetailRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DetailService {
    private final UserDetailRepository userDetailRepository;

    @Autowired
    public DetailService(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    // Создание нового UserDetail
    public UserDetail createUserDetail(UserDetail userDetail)  throws TaskException {
        return userDetailRepository.save(userDetail);
    }

    // Получение всех UserDetail
    public List<UserDetail> getAllUserDetails() {
        List<UserDetail> userDetailList = (List<UserDetail>) userDetailRepository.findAll();
        if (userDetailList.isEmpty()) throw new TaskException("userDetailList is empty");
        return userDetailList;
    }

    // Получение UserDetail по ID
    public UserDetail getUserDetailById(long id) {
        if (!userDetailRepository.existsById(id)) throw new TaskException("UserDetail " + id + " not found");
        return userDetailRepository.findById(id);
    }

    // Обновление UserDetail
    public UserDetail updateUserDetail(long id, LocalDate localDate) {
        if (!userDetailRepository.existsById(id)) throw new TaskException("UserDetail " + id + " not found");
        UserDetail userDetail = userDetailRepository.findById(id);
        userDetail.setBirthDate(localDate);
        return userDetailRepository.save(userDetail);
    }

    // Удаление UserDetail
    public void deleteUserDetail(long id) {
        if (!userDetailRepository.existsById(id)) throw new TaskException("UserDetail " + id + " not found");
        userDetailRepository.deleteById(id);
    }

}

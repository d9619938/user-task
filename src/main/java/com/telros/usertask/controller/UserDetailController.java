package com.telros.usertask.controller;

import com.telros.usertask.entity.User;
import com.telros.usertask.entity.UserDetail;
import com.telros.usertask.exception.TaskException;
import com.telros.usertask.service.DetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/details")
public class UserDetailController {
    private final DetailService detailService;

    @Autowired
    public UserDetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    // Получение UserDetail по ID
    @ResponseBody
    @GetMapping("/get")  // РАБОТАЕТ
    public UserDetail getDetailJSON(@RequestParam() long id) {
        try{
            return detailService.getUserDetailById(id);
        } catch (TaskException t) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, t.getMessage());
        }
    }
    // Создание нового UserDetail
    @PostMapping
    public UserDetail createUserDetail(@RequestBody UserDetail userDetail) {
        return detailService.createUserDetail(userDetail);
    }

    // Получение всех UserDetail
    @GetMapping
    public List<UserDetail> getAllUserDetails() {
        return detailService.getAllUserDetails();
    }

    // Обновление UserDetail
    @PutMapping("/{id}")
    public ResponseEntity<UserDetail> updateUserDetail(@PathVariable long id, @RequestBody LocalDate birthDate) {
        return ResponseEntity.ok(detailService.updateUserDetail(id, birthDate));
    }

    // Удаление UserDetail
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDetail(@PathVariable long id) {
        detailService.deleteUserDetail(id);
        return ResponseEntity.noContent().build();
    }

}

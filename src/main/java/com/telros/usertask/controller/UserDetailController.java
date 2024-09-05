package com.telros.usertask.controller;

import com.telros.usertask.entity.User;
import com.telros.usertask.entity.UserDetail;
import com.telros.usertask.exception.TaskException;
import com.telros.usertask.service.DetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Controller("details")
public class UserDetailController {
    private final DetailService detailService;

    @Autowired
    public UserDetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @ResponseBody
    @GetMapping("/get")  // РАБОТАЕТ
    public UserDetail getDetailJSON(@RequestParam() long id) {
        try{
            return detailService.getDetail(id);
        } catch (TaskException t) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, t.getMessage());
        }
    }
}

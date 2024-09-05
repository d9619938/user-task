package com.telros.usertask.controller;

import com.telros.usertask.entity.User;
import com.telros.usertask.exception.TaskException;
import com.telros.usertask.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller("/account")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/registration")
    public String getRegistrationFormHTML(User user){
        if (isAuthenticated()) return "redirect:/account";
        return "registrationForm";
    }
    @PostMapping("/registration")
    public String addNewUserHTML(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "registrationForm";
        try {
            userService.saveUser(user);
        } catch (TaskException e) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
            return "redirect:/account/registration?user_exists";
        }
        return "redirect:/account/login";
    }
    @GetMapping("login")
    public String login() {
        if (isAuthenticated()) return "redirect:/account";
        return "login";
    }

    @GetMapping
    public String account(){
        return "account";
    }

    @GetMapping("/getAll")
    public String getAll(Model model){
        List<User> usersList = userService.getAllUsers();
        model.addAttribute("users", usersList);
        return "register";
    }
    @GetMapping("/add")
    public String getUserAddForm(User user){
        return "userAddForm";
    }
    @PostMapping("/add")
    public String add(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "userAddForm";
       try {
           userService.createdUser(user);
        } catch (TaskException t) {
           return "redirect:/account/add?user_exists";
        }
       return "redirect:/account/getAll";
    }

    @DeleteMapping("/del/{id}")
    public void deleteUser(@PathVariable @Valid Long id) {
        userService.deleteUser(id);
    }

    @ResponseBody
    @PutMapping("/update") // РАБОТАЕТ (нужно передать в Body(raw) полный json объекта
    // {"id":"33","lastName":"...",...})
    public ResponseEntity<Void> updateUser (@RequestBody @Valid User user)  {
        try {
            userService.updateUser(user);
            return ResponseEntity.ok().build();
        } catch (TaskException t){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, t.getMessage());
        }
    }
    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class. isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}

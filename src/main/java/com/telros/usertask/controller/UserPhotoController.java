package com.telros.usertask.controller;

import com.telros.usertask.entity.User;
import com.telros.usertask.entity.UserPhoto;
import com.telros.usertask.exception.TaskException;
import com.telros.usertask.repository.UserRepository;
import com.telros.usertask.service.UserPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/photos")
public class UserPhotoController {
    private final UserPhotoService userPhotoService;
    private final UserRepository userRepository;

    @Autowired
    public UserPhotoController(UserPhotoService userPhotoService,
                               UserRepository userRepository) {
        this.userPhotoService = userPhotoService;
        this.userRepository = userRepository;
    }
    // Сохранение UserPhoto
    @PostMapping
    public ResponseEntity<String> saveUserPhoto(@RequestParam("image") MultipartFile image, @RequestParam("userId") long userId) {
        // Получаем пользователя из базы данных
        User user = userRepository.findById(userId).orElseThrow(() -> new TaskException("Пользователь не найден"));
        String photoId = userPhotoService.saveImage(image, user);
        return ResponseEntity.ok(photoId);
    }

    // Получение всех UserPhoto
    @GetMapping
    public ResponseEntity<List<UserPhoto>> getAllUserPhotos() {
        List<UserPhoto> userPhotos = userPhotoService.getAllUsersPhoto();
        return ResponseEntity.ok(userPhotos);
    }

    // Получение UserPhoto по ID
    @GetMapping("/{id}")
    public ResponseEntity<UserPhoto> getUserPhotoById(@PathVariable long id) {
        UserPhoto userPhoto = userPhotoService.getUserPhotoById(id);
        return ResponseEntity.ok(userPhoto);
    }

    // Обновление UserPhoto
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserPhoto(@PathVariable long id, @RequestBody UserPhoto photo) {
        photo.setId(id); // Устанавливаем ID для обновления
        userPhotoService.update(photo);
        return ResponseEntity.noContent().build();
    }

    // Удаление UserPhoto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserPhoto(@PathVariable long id) {
        userPhotoService.deleteUserPhoto(id);
        return ResponseEntity.noContent().build();
    }


}

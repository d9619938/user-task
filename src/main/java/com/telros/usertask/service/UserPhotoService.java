package com.telros.usertask.service;

import com.telros.usertask.entity.User;
import com.telros.usertask.entity.UserDetail;
import com.telros.usertask.entity.UserPhoto;
import com.telros.usertask.exception.TaskException;
import com.telros.usertask.repository.UserPhotoRepository;
import com.telros.usertask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserPhotoService {



    private final UserPhotoRepository userPhotoRepository;
    private final UserRepository userRepository;

@Autowired
    public UserPhotoService(UserPhotoRepository userPhotoRepository, UserRepository userRepository) {
        this.userPhotoRepository = userPhotoRepository;
        this.userRepository = userRepository;
    }
    //  Сохранение UserPhoto
    public String saveImage (MultipartFile image, User user) throws TaskException {
    if (!userRepository.existsById(user.getId())){
        throw new TaskException("Пользователь не существует");
    }
    UserPhoto photo = userPhotoRepository.findByUser(user).orElseGet(() -> {
        UserPhoto photoNew = new UserPhoto();
        photoNew.setUser(user);
        return photoNew;
    });
    photo.setPhotoPath(saveFile(image));
    userPhotoRepository.save(photo);
    return photo.getId().toString();
}
    // Получение всех UserPhoto
    public List<UserPhoto> getAllUsersPhoto() {
        List<UserPhoto> userPhotoList = (List<UserPhoto>) userPhotoRepository.findAll();
        if (userPhotoList.isEmpty()) throw new TaskException("UserPhotoList is empty");
        return userPhotoList;
    }

    // Получение UserPhoto по ID
    public UserPhoto getUserPhotoById(long id) {
        if (!userPhotoRepository.existsById(id)) throw new TaskException("UserPhoto " + id + " not found");
        return userPhotoRepository.findById(id).get();
    }

    //  Обновление UserPhoto
    public void update (UserPhoto photo) throws TaskException {
        if (!userPhotoRepository.existsById(photo.getId())){
            throw new TaskException("UserPhoto " + photo.getId() + " not found");
        }
        userPhotoRepository.save(photo);
    }

    // Удаление UserPhoto
    public void deleteUserPhoto(long id) {
        if (!userPhotoRepository.existsById(id)) throw new TaskException("UserPhoto " + id + " not found");
        userPhotoRepository.deleteById(id);
    }


//  Вспомогательные методы проверки и сохранения фото
    private String saveFile(MultipartFile image) throws TaskException {
        String check = checkFile(image);
        if(!check.equals("true")) throw new TaskException("Файл не прошел валидацию." + check);
        String fileName = "src/main/resources/static/images/"
                + UUID.randomUUID() + "-" + image.getOriginalFilename();
        try {
            try(OutputStream os = new FileOutputStream(fileName)) {
                os.write(image.getBytes());
            }
            return parseFileName(fileName);
        } catch (IOException e) {
            throw new TaskException("Проблема загрузки файла " + e.getMessage());
        }
    }
    private String checkFile(MultipartFile image) {
        if(image.isEmpty()) return "Вам не удалось загрузить" + image.getOriginalFilename() + " потому, что файл пустой.";
        if(image.getSize() >= 10*1024*1024) return "Вам не удалось загрузить " + image.getOriginalFilename() + " потому, что файл" +
                " превышает допустимый размер (10 Мб).";
        if(!Objects.requireNonNull(image.getOriginalFilename()).endsWith(".jpg")) return "Вам не удалось загрузить"
                + image.getOriginalFilename() + " потому, что тип файла не jpeg";
        return "true";
    }

    private String parseFileName (String fileName) {
        String[] strings = fileName.split("/");
        return strings[strings.length-1];
    }
}

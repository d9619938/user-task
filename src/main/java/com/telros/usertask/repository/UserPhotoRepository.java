package com.telros.usertask.repository;

import com.telros.usertask.entity.User;
import com.telros.usertask.entity.UserPhoto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserPhotoRepository extends CrudRepository<UserPhoto, Long> {
    Optional<UserPhoto> findByUser(User user);
    Optional<UserPhoto> findByPhotoPath(String photoPath);
    boolean existsUserPhotoByUser(User user);
}

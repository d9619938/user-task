package com.telros.usertask.repository;

import com.telros.usertask.entity.User;
import com.telros.usertask.entity.UserDetail;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserDetailRepository extends CrudRepository<UserDetail, Long> {
    boolean existsById(long id);

    Optional<UserDetail> findByUser(User user);
    UserDetail findById(long id);
    Optional<List<UserDetail>> findByBirthDate(LocalDate birthDate);
}

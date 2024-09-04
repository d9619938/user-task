package com.telros.usertask.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "tb_user_datails")
public class UserDetail {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column (nullable = false)
    private LocalDate birthDate;

    public UserDetail() {
    }
    public UserDetail(long id, User user, LocalDate birthDate) {
        this.id = id;
        this.user = user;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}

package com.telros.usertask.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user_photos")
public class UserPhoto {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn (name = "user_id")
    private User user;

    @Column ()
    private String photoPath;

    public UserPhoto() {
    }

    public UserPhoto(Long id, User user, String photoPath) {
        this.id = id;
        this.user = user;
        this.photoPath = photoPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}

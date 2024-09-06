package com.telros.usertask.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "tb_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    @NotNull(message = "name not null")
    @NotEmpty (message = "name not empty")
    @Column(nullable = false, unique = true)
    private String username;
    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String password;
//    @NotBlank(message = "lastName not null")
//    @Column(nullable = false)
private String lastName;
//    @NotBlank(message = "firstName not null")
//    @Column(nullable = false)
private String firstName;
//    @NotBlank(message = "middleName not null")
//    @Column(nullable = false)
private String middleName;
    @NotNull(message = "email not null")
    @Email
private String email;
//    @NotBlank(message = "phoneNumber not null")
//    @Positive
//    @Max(99999999999L)
//    @Column(unique = true, nullable = false)
private long phoneNumber;
    @ManyToOne
    private Role role;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "name not null") @NotEmpty(message = "name not empty") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "name not null") @NotEmpty(message = "name not empty") String username) {
        this.username = username;
    }

    public @NotNull @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @NotEmpty String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public @NotNull(message = "email not null") @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "email not null") @Email String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

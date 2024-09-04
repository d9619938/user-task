package com.telros.usertask.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "tb_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    @NotBlank(message = "lastName not null")
    @Column(nullable = false)
private String lastName;
    @NotBlank(message = "firstName not null")
    @Column(nullable = false)
private String firstName;
    @NotBlank(message = "middleName not null")
    @Column(nullable = false)
private String middleName;
    @NotNull(message = "email not null")
    @Email
    @Column(unique = true)
private String email;
    @NotBlank(message = "phoneNumber not null")
    @Positive
    @Max(99999999999L)
    @Column(unique = true, nullable = false)
private long phoneNumber;

    public User() {
    }
    public User(Long id, String lastName, String firstName, String middleName, String email, long phoneNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "lastName not null") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "lastName not null") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "firstName not null") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "firstName not null") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "middleName not null") String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(@NotBlank(message = "middleName not null") String middleName) {
        this.middleName = middleName;
    }

    public @NotNull(message = "email not null") @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "email not null") @Email String email) {
        this.email = email;
    }

    @NotBlank(message = "phoneNumber not null")
    @Positive
    @Max(99999999999L)
    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "phoneNumber not null") @Positive @Max(99999999999L) long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

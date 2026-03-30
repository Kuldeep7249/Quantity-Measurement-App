package com.quantitymeasurement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "name cannot be null")
    private String name;
    private String phoneNumber;
    @NotNull(message = "Email cannot be null")
    @Column(unique = true)
    private String email;
    @NotNull(message = "password cannot be null")
    private String password;
    public UserEntity() {}
    public UserEntity(String name, String phoneNumber, String email, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

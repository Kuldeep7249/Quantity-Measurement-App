package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.User;

import jakarta.validation.Valid;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();

    String loginUser(UserDTO user);

    User createUser(@Valid User user);

    void deleteUser(Long id);
}
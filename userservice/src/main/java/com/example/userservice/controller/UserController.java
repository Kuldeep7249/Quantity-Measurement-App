package com.example.userservice.controller;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get user by ID (Feign will call this)
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user) {

        String token = userService.loginUser(user);

        return ResponseEntity.ok(
                Map.of(
                        "message", "User Login Successful",
                        "token", token
                )
        );
    }

    // Signup
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {

        userService.createUser(user);

        return ResponseEntity.ok(
                Map.of("message", "User Signup Successful")
        );
    }

    // Delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok(
                Map.of("message", "User Deleted Successfully")
        );
    }
}
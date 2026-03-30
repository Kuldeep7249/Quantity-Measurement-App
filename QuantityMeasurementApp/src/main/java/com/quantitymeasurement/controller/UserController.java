package com.quantitymeasurement.controller;

import com.quantitymeasurement.dto.UserDTO;
import com.quantitymeasurement.entity.UserEntity;
import com.quantitymeasurement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user) {

        String token = service.loginUser(user);

        return ResponseEntity.ok(
                Map.of(
                        "message", "User Login Successful",
                        "token", token
                )
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserEntity user) {

        service.createUser(user);

        return ResponseEntity.ok(
                Map.of("message", "User Signup Successful")
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.ok(
                Map.of("message", "User Deleted Successfully")
        );
    }
}
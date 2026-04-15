package com.example.ConversionService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.example.ConversionService.dto.UserDTO;

@FeignClient(name = "user-service", url = "http://localhost:8085")
public interface UserClient {

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable Long id);
}
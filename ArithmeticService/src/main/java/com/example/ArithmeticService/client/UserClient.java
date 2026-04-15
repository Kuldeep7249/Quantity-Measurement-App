package com.example.ArithmeticService.client;

import com.example.ArithmeticService.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "http://localhost:8085")
public interface UserClient {

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable Long id);
}
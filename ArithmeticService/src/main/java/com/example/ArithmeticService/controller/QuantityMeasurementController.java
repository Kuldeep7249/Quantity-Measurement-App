package com.example.ArithmeticService.controller;

import com.example.ArithmeticService.dto.QuantityDTO;
import com.example.ArithmeticService.service.QuantityMeasurementServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measurements")
public class QuantityMeasurementController {

    @Autowired
    private QuantityMeasurementServiceImpl service;

    @PostMapping
    public QuantityDTO save(@RequestBody QuantityDTO dto, HttpServletRequest request) {
        // Read userId from JWT via JwtAuthFilter — overrides whatever client sent
        Long userId = (Long) request.getAttribute("userId");
        dto.setUserId(userId);
        return service.save(dto);
    }

    @GetMapping
    public List<QuantityDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/history/{userId}")
    public List<QuantityDTO> getHistoryByUser(@PathVariable Long userId) {
        return service.findByUserId(userId);
    }

    @DeleteMapping
    public String deleteAll() {
        service.deleteAllMeasurements();
        return "All the measurements Deleted";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }
}
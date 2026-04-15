package com.example.ComparisonService.controller;


import com.example.ComparisonService.dto.CompareRequestDTO;
import com.example.ComparisonService.dto.CompareResponseDTO;
import com.example.ComparisonService.service.ComparisonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compare")
public class ComparisonController {

    @Autowired
    private ComparisonService comparisonService;

    @PostMapping
    public ResponseEntity<?> compare(@RequestBody CompareRequestDTO request,
                                     HttpServletRequest httpRequest) {
        try {
            // Read userId from JWT via JwtAuthFilter
            Long userId = (Long) httpRequest.getAttribute("userId");
            CompareResponseDTO response = comparisonService.compare(request, userId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid Argument: " + e.getMessage());
        }
    }
}
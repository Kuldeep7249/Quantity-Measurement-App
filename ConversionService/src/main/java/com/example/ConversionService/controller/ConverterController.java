package com.example.ConversionService.controller;

import com.example.ConversionService.dto.ConvertResponseDTO;
import com.example.ConversionService.enums.UnitType;
import com.example.ConversionService.service.UnitConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ConverterController {
    @Autowired
    private UnitConverterService convertService;
    @GetMapping("/convert")
    public ConvertResponseDTO convert(
            @RequestParam("type") UnitType type,
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("value") double value,
            @RequestParam(required = false) Long userId
    ) {

        // 👉 Your conversion logic here
        double result = convertService.convert(type, from, to, value, userId); // replace with real logic

        return new ConvertResponseDTO(result);
    }
    @GetMapping("/convert/history/{userid}")
    public ResponseEntity<?> getHistory(@PathVariable Long userId) {

        return ResponseEntity.ok(
                convertService.getHistoryByUserId(userId)
        );
    }
}
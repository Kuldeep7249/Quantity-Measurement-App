package com.example.ComparisonService.client;

import com.example.ComparisonService.dto.ConvertResponseDTO;
import com.example.ComparisonService.enums.UnitType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "converter-service", url = "http://localhost:8083")
public interface ConverterClient {

    @GetMapping("/api/convert")
    ConvertResponseDTO convert(
            @RequestParam("type") UnitType unitType,
            @RequestParam("from") String fromUnit,
            @RequestParam("to") String toUnit,
            @RequestParam("value") double value,
            @RequestParam("userId") Long userId
    );
}
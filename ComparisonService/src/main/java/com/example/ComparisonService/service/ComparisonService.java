package com.example.ComparisonService.service;

import com.example.ComparisonService.client.ConverterClient;
import com.example.ComparisonService.dto.CompareRequestDTO;
import com.example.ComparisonService.dto.CompareResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComparisonService {

    @Autowired
    private ConverterClient converterClient;

    public CompareResponseDTO compare(CompareRequestDTO request, Long userId) {

        if (request.getUnitType() == null) {
            throw new IllegalArgumentException("Unit type must not be null");
        }
        if (request.getFromUnit1() == null || request.getFromUnit2() == null || request.getToUnit() == null) {
            throw new IllegalArgumentException("fromUnit1, fromUnit2 and toUnit must not be null");
        }

        // ✅ FIX: Use DTO response
        double result1 = converterClient.convert(
                request.getUnitType(),
                request.getFromUnit1(),
                request.getToUnit(),
                request.getValue1(),
                userId
        ).getResult();

        double result2 = converterClient.convert(
                request.getUnitType(),
                request.getFromUnit2(),
                request.getToUnit(),
                request.getValue2(),
                userId
        ).getResult();

        String larger;
        String message;

        if (result1 > result2) {
            larger = "FIRST";
            message = request.getValue1() + " " + request.getFromUnit1()
                    + " is greater than "
                    + request.getValue2() + " " + request.getFromUnit2()
                    + " (in " + request.getToUnit() + ": " + result1 + " vs " + result2 + ")";
        } else if (result2 > result1) {
            larger = "SECOND";
            message = request.getValue2() + " " + request.getFromUnit2()
                    + " is greater than "
                    + request.getValue1() + " " + request.getFromUnit1()
                    + " (in " + request.getToUnit() + ": " + result2 + " vs " + result1 + ")";
        } else {
            larger = "EQUAL";
            message = "Both values are equal in " + request.getToUnit() + ": " + result1;
        }

        return new CompareResponseDTO(result1, result2, request.getToUnit(), larger, message);
    }
}
package com.example.ArithmeticService.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operation;
    private double operand1;
    private double operand2;
    private double result;

    // ✅ KEEP only userId
    private Long userId;
}
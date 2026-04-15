package com.example.ConversionService.entity;

import com.example.ConversionService.enums.UnitType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "conversions")
public class ConversionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    private String fromUnit;
    private String toUnit;
    private double inputValue;
    private double outputValue;

    // ✅ ONLY store userId
    @Column(name = "user_id")
    private Long userId;

    public ConversionEntity(UnitType unitType, String fromUnit, String toUnit, double inputValue, double outputValue) {
        this.unitType = unitType;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.inputValue = inputValue;
        this.outputValue = outputValue;
    }
}
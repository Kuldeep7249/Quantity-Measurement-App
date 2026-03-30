package com.quantitymeasurement.entity;

import com.quantitymeasurement.enums.UnitType;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "conversions")
public class ConversionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // LENGTH, VOLUME, MASS
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    private String fromUnit;
    private String toUnit;
    private double inputValue;
    private double outputValue;

    public ConversionEntity(UnitType unitType, String fromUnit, String toUnit, double inputValue, double outputValue) {
        this.id = id;
        this.unitType = unitType;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.inputValue = inputValue;
        this.outputValue = outputValue;
    }

    public double getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(double outputValue) {
        this.outputValue = outputValue;
    }

    public double getInputValue() {
        return inputValue;
    }

    public void setInputValue(double inputValue) {
        this.inputValue = inputValue;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
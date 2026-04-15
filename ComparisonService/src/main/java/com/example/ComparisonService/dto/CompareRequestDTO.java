package com.example.ComparisonService.dto;


import com.example.ComparisonService.enums.UnitType;

public class CompareRequestDTO {

    private UnitType unitType;

    private String fromUnit1;
    private double value1;

    private String fromUnit2;
    private double value2;

    private String toUnit;

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public String getFromUnit1() {
        return fromUnit1;
    }

    public void setFromUnit1(String fromUnit1) {
        this.fromUnit1 = fromUnit1;
    }

    public double getValue1() {
        return value1;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }

    public String getFromUnit2() {
        return fromUnit2;
    }

    public void setFromUnit2(String fromUnit2) {
        this.fromUnit2 = fromUnit2;
    }

    public double getValue2() {
        return value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }
}
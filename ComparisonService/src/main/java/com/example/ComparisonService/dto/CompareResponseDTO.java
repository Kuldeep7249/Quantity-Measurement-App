package com.example.ComparisonService.dto;

public class CompareResponseDTO {

    private double convertedValue1;
    private double convertedValue2;
    private String toUnit;

    // "FIRST", "SECOND", "EQUAL"
    private String larger;

    private String message;

    public CompareResponseDTO(double convertedValue1, double convertedValue2, String toUnit, String larger, String message) {
        this.convertedValue1 = convertedValue1;
        this.convertedValue2 = convertedValue2;
        this.toUnit = toUnit;
        this.larger = larger;
        this.message = message;
    }

    public double getConvertedValue1() {
        return convertedValue1;
    }

    public void setConvertedValue1(double convertedValue1) {
        this.convertedValue1 = convertedValue1;
    }

    public double getConvertedValue2() {
        return convertedValue2;
    }

    public void setConvertedValue2(double convertedValue2) {
        this.convertedValue2 = convertedValue2;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }

    public String getLarger() {
        return larger;
    }

    public void setLarger(String larger) {
        this.larger = larger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
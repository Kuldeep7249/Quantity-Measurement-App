package com.example.ConversionService.dto;

public class ConvertResponseDTO {

    private double result;

    public ConvertResponseDTO() {
    }

    public ConvertResponseDTO(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
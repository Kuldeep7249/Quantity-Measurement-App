package com.quantitymeasurement.dto;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
public class QuantityDTO {
    private String operation;
    private double operand1;
    private double operand2;
    private double result;

    public QuantityDTO(double result, double operand2, double operand1, String operation) {
        this.result = result;
        this.operand2 = operand2;
        this.operand1 = operand1;
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
package com.example.ArithmeticService.dto;

import lombok.Data;

@Data
public class QuantityDTO {

    private String operation;
    private double operand1;
    private double operand2;
    private double result;
    private Long userId;

    // Required by Jackson to deserialize incoming POST request body
    public QuantityDTO() {}

    // Used internally when building response from saved entity
    public QuantityDTO(double result, double operand2, double operand1, String operation, Long userId) {
        this.result    = result;
        this.operand2  = operand2;
        this.operand1  = operand1;
        this.operation = operation;
        this.userId    = userId;
    }

    // Backward-compatible constructor (without userId)
    public QuantityDTO(double result, double operand2, double operand1, String operation) {
        this.result    = result;
        this.operand2  = operand2;
        this.operand1  = operand1;
        this.operation = operation;
    }

    public String getOperation()       { return operation; }
    public void setOperation(String o) { this.operation = o; }

    public double getOperand1()        { return operand1; }
    public void setOperand1(double o)  { this.operand1 = o; }

    public double getOperand2()        { return operand2; }
    public void setOperand2(double o)  { this.operand2 = o; }

    public double getResult()          { return result; }
    public void setResult(double r)    { this.result = r; }

    public Long getUserId()            { return userId; }
    public void setUserId(Long u)      { this.userId = u; }
}
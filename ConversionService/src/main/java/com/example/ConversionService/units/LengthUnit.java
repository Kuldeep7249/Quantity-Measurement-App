package com.example.ConversionService.units;

public enum LengthUnit implements IMeasurable {

    METER(39.3701),
    FOOT(12.0),
    INCH(1.0),
    YARD(36.0),
    CENTIMETER(0.393701),
    KILOMETER(39370.07);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public boolean supportsArithmetic() {
        return true;
    }
}
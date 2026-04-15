package com.example.ConversionService.units;

public enum VolumeUnit implements IMeasurable {

    LITER(1.0),
    MILLILITER(0.001),
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
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
package com.quantitymeasurement.service;

import com.quantitymeasurement.entity.ConversionEntity;
import com.quantitymeasurement.enums.UnitType;
import com.quantitymeasurement.repository.ConversionRepository;
import com.quantitymeasurement.units.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitConverterService {

    @Autowired
    private ConversionRepository repository;

    public Double convert(UnitType unitType, String fromUnit, String toUnit, double value) {
        double result;
        switch (unitType) {

            case LENGTH:
                result= convertLength(fromUnit, toUnit, value);
                break;
            case VOLUME:
                 result=convertVolume(fromUnit, toUnit, value);
                break;
            case WEIGHT:
                result= convertMass(fromUnit, toUnit, value);
                break;
            case TEMPERATURE:
                 result= convertTemperature(fromUnit, toUnit, value);
                 break;
            default:
                result=0;
//                throw new IllegalArgumentException("Invalid Unit Type");
        }
        ConversionEntity c=new ConversionEntity(unitType,fromUnit,toUnit,value,result);
        repository.save(c);
        return result;
    }

    // 🔹 LENGTH
    private double convertLength(String from, String to, double value) {
        LengthUnit fromUnit = LengthUnit.valueOf(from);
        LengthUnit toUnit = LengthUnit.valueOf(to);

        double baseValue = fromUnit.convertToBaseUnit(value);
        return baseValue / toUnit.convertToBaseUnit(1);
    }

    // 🔹 VOLUME
    private double convertVolume(String from, String to, double value) {
        VolumeUnit fromUnit = VolumeUnit.valueOf(from);
        VolumeUnit toUnit = VolumeUnit.valueOf(to);

        double baseValue = fromUnit.convertToBaseUnit(value);
        return baseValue / toUnit.convertToBaseUnit(1);
    }

    // 🔹 MASS
    private double convertMass(String from, String to, double value) {
        WeightUnit fromUnit = WeightUnit.valueOf(from);
        WeightUnit toUnit = WeightUnit.valueOf(to);

        double baseValue = fromUnit.convertToBaseUnit(value);
        return baseValue / toUnit.convertToBaseUnit(1);
    }
    private double convertTemperature(String from, String to, double value) {

        TemperatureUnit fromUnit = TemperatureUnit.valueOf(from);
        TemperatureUnit toUnit = TemperatureUnit.valueOf(to);

        double baseValue = fromUnit.convertToBaseUnit(value);
        return toUnit.convertFromBaseUnit(baseValue);
    }

}
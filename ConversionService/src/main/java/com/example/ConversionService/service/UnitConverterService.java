package com.example.ConversionService.service;

import com.example.ConversionService.client.UserClient;
import com.example.ConversionService.dto.UserDTO;
import com.example.ConversionService.entity.ConversionEntity;
import com.example.ConversionService.enums.UnitType;
import com.example.ConversionService.repository.ConversionRepository;
import com.example.ConversionService.units.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitConverterService {

    @Autowired
    private ConversionRepository repository;

    @Autowired
    private UserClient userClient;   // ✅ Feign client

    public Double convert(UnitType unitType, String fromUnit, String toUnit, double value) {
        return convert(unitType, fromUnit, toUnit, value, null);
    }

    public Double convert(UnitType unitType, String fromUnit, String toUnit,
                          double value, Long userId) {

        double result;

        switch (unitType) {
            case LENGTH:
                result = convertLength(fromUnit, toUnit, value);
                break;
            case VOLUME:
                result = convertVolume(fromUnit, toUnit, value);
                break;
            case WEIGHT:
                result = convertMass(fromUnit, toUnit, value);
                break;
            case TEMPERATURE:
                result = convertTemperature(fromUnit, toUnit, value);
                break;
            default:
                result = 0;
        }

        ConversionEntity c = new ConversionEntity(unitType, fromUnit, toUnit, value, result);

        // ✅ Correct microservice logic
        if (userId != null) {
            UserDTO user = userClient.getUserById(userId);

            if (user == null) {
                throw new RuntimeException("User not found with id: " + userId);
            }

            c.setUserId(userId);   // ✅ store only ID
        }

        repository.save(c);
        return result;
    }

    public List<ConversionEntity> getHistoryByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<ConversionEntity> getHistoryByUserIdAndType(Long userId, UnitType unitType) {
        return repository.findByUserIdAndUnitType(userId, unitType);
    }

    private double convertLength(String from, String to, double value) {
        LengthUnit fromUnit = LengthUnit.valueOf(from);
        LengthUnit toUnit = LengthUnit.valueOf(to);
        double baseValue = fromUnit.convertToBaseUnit(value);
        return baseValue / toUnit.convertToBaseUnit(1);
    }

    // VOLUME
    private double convertVolume(String from, String to, double value) {
        VolumeUnit fromUnit = VolumeUnit.valueOf(from);
        VolumeUnit toUnit = VolumeUnit.valueOf(to);
        double baseValue = fromUnit.convertToBaseUnit(value);
        return baseValue / toUnit.convertToBaseUnit(1);
    }

    // WEIGHT
    private double convertMass(String from, String to, double value) {
        WeightUnit fromUnit = WeightUnit.valueOf(from);
        WeightUnit toUnit = WeightUnit.valueOf(to);
        double baseValue = fromUnit.convertToBaseUnit(value);
        return baseValue / toUnit.convertToBaseUnit(1);
    }

    // TEMPERATURE
    private double convertTemperature(String from, String to, double value) {
        TemperatureUnit fromUnit = TemperatureUnit.valueOf(from);
        TemperatureUnit toUnit = TemperatureUnit.valueOf(to);
        double baseValue = fromUnit.convertToBaseUnit(value);
        return toUnit.convertFromBaseUnit(baseValue);
    }

}
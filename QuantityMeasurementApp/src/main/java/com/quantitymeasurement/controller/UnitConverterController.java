package com.quantitymeasurement.controller;

import com.quantitymeasurement.enums.UnitType;
import com.quantitymeasurement.service.UnitConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/convert")
public class UnitConverterController {

    @Autowired
    private UnitConverterService service;


    // Example:
    // /api/convert?type=LENGTH&from=METER&to=KILOMETER&value=1000
    @GetMapping
    public String  convert(
            @RequestParam("type") UnitType unitType,
            @RequestParam("from") String fromUnit,
            @RequestParam("to") String toUnit,
            @RequestParam("value") double value) {
        try {
            service.convert(unitType, fromUnit, toUnit, value);
            return "Data Added Successfully";
        }
        catch (IllegalArgumentException e){
            return "Invalid Argument";
        }
    }
}
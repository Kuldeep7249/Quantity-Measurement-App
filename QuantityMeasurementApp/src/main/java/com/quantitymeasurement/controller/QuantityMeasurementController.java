package com.quantitymeasurement.controller;

import com.quantitymeasurement.dto.QuantityDTO;
import com.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.service.DTOService;
import com.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measurements")
public class QuantityMeasurementController {

   DTOService q=new DTOService();
   @Autowired
    private QuantityMeasurementServiceImpl service;

    @PostMapping
    public QuantityDTO save(@RequestBody QuantityMeasurementEntity entity) {
        QuantityDTO p=q.convertToDTO(entity);
        return service.save(p);
    }

    @GetMapping
    public List<QuantityDTO> getAll() {
        return service.findAll();
    }
    @DeleteMapping
    public String deleteAll(){
        service.deleteAllMeasurements();
        return "All the measurements Deleted";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }
}
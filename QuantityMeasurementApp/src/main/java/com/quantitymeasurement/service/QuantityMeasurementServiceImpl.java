package com.quantitymeasurement.service;

import com.quantitymeasurement.dto.QuantityDTO;
import com.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl {

   @Autowired
    private  QuantityMeasurementRepository repository;

    public QuantityDTO save(QuantityDTO dto) {

        double op1 = dto.getOperand1();
        double op2 = dto.getOperand2();
        String operation = dto.getOperation();

        double result = 0;

        switch (operation.toLowerCase()) {
            case "add":
                result = op1 + op2;
                break;
            case "subtract":
                result = op1 - op2;
                break;
            case "multiply":
                result = op1 * op2;
                break;
            case "divide":
                if (op2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                result = op1 / op2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }

        // ✅ Convert DTO → Entity
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(dto.getOperation(),dto.getOperand1(),dto.getOperand2(),dto.getResult());


        // ✅ Save Entity
        QuantityMeasurementEntity savedEntity = repository.save(entity);

        // ✅ Convert back Entity → DTO
        QuantityDTO response = new QuantityDTO(entity.getResult(),entity.getOperand2(),entity.getOperand1(),entity.getOperation());
        return response;
    }

    public List<QuantityDTO> findAll() {
        List<QuantityMeasurementEntity> entities = repository.findAll();

        return entities.stream().map(entity -> {
            QuantityDTO dto = new QuantityDTO(entity.getResult(),entity.getOperand2(),entity.getOperand1(),entity.getOperation());

            return dto;
        }).toList();
    }

    public void deleteAllMeasurements() {

        repository.deleteAll();
    }
    public String deleteById(Long id){
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return "Data deleted";
        }
        else{
            return "No data is present";
        }
    }

}
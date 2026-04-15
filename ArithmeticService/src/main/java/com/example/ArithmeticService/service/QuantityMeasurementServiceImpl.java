package com.example.ArithmeticService.service;

import com.example.ArithmeticService.client.UserClient;
import com.example.ArithmeticService.dto.QuantityDTO;
import com.example.ArithmeticService.dto.UserDTO;
import com.example.ArithmeticService.entity.QuantityMeasurementEntity;
import com.example.ArithmeticService.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl {

    @Autowired
    private QuantityMeasurementRepository repository;

    @Autowired
    private UserClient userClient; // ✅ Feign Client

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

        // ✅ Validate user using Feign
        if (dto.getUserId() != null) {
            UserDTO user = userClient.getUserById(dto.getUserId());

            if (user == null) {
                throw new RuntimeException("User not found with id: " + dto.getUserId());
            }
        }

        // ✅ Save only userId (NOT entity)
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation(operation);
        entity.setOperand1(op1);
        entity.setOperand2(op2);
        entity.setResult(result);
        entity.setUserId(dto.getUserId());

        QuantityMeasurementEntity savedEntity = repository.save(entity);

        return new QuantityDTO(
                savedEntity.getResult(),
                savedEntity.getOperand2(),
                savedEntity.getOperand1(),
                savedEntity.getOperation(),
                savedEntity.getUserId()
        );
    }

    public List<QuantityDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public List<QuantityDTO> findByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(this::toDTO)
                .toList();
    }

    public void deleteAllMeasurements() {
        repository.deleteAll();
    }

    public String deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Data deleted";
        } else {
            return "No data is present";
        }
    }

    private QuantityDTO toDTO(QuantityMeasurementEntity entity) {
        return new QuantityDTO(
                entity.getResult(),
                entity.getOperand2(),
                entity.getOperand1(),
                entity.getOperation(),
                entity.getUserId()
        );
    }
}
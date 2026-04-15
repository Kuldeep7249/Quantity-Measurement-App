package com.example.ArithmeticService.repository;

import com.example.ArithmeticService.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {

    // Fetch all measurement history for a specific user
    List<QuantityMeasurementEntity> findByUserId(Long userId);
}
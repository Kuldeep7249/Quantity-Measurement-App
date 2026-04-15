package com.example.ConversionService.repository;


import com.example.ConversionService.entity.ConversionEntity;
import com.example.ConversionService.enums.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversionRepository extends JpaRepository<ConversionEntity, Long> {

    // Fetch all conversion history for a specific user
    List<ConversionEntity> findByUserId(Long userId);

    // Fetch conversion history filtered by unit type for a specific user
    List<ConversionEntity> findByUserIdAndUnitType(Long userId, UnitType unitType);


}
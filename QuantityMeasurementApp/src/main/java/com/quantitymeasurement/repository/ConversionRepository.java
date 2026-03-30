package com.quantitymeasurement.repository;

import com.quantitymeasurement.entity.ConversionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversionRepository extends JpaRepository<ConversionEntity, Long> {
}
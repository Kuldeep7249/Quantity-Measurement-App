package com.quantitymeasurement.service;

import com.quantitymeasurement.dto.QuantityDTO;
import com.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DTOService {

    public QuantityDTO convertToDTO(QuantityMeasurementEntity q){
        QuantityDTO d= new QuantityDTO(q.getResult(),q.getOperand2(),q.getOperand1(),q.getOperation());
                return d;
    }
}

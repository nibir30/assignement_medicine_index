package com.nibir.medicine_index.service;

import com.nibir.medicine_index.model.ManufactureModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ManufactureService {
    ResponseEntity<?> getAllManufacturer();

    ResponseEntity<?> updateManufacturer(ManufactureModel manufactureModel);

    ResponseEntity<?> deleteManufacturer(Long id);
}

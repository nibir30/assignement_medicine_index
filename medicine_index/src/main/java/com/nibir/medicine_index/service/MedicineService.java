package com.nibir.medicine_index.service;

import com.nibir.medicine_index.model.MedicineModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MedicineService {
    ResponseEntity<?> getAllMedicine();

    ResponseEntity<?> updateMedicine(MedicineModel manufactureModel);

    ResponseEntity<?> deleteMedicine(Long id);
}

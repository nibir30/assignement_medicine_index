package com.nibir.medicine_index.service;

import com.nibir.medicine_index.data.ReqData.AddMedicineReqData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MedicineService {
    ResponseEntity<?> getAllMedicine();

    ResponseEntity<?> updateMedicine(AddMedicineReqData manufactureModel);

    ResponseEntity<?> deleteMedicine(Long id);
}
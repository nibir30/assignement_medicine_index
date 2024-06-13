package com.nibir.medicine_index.serviceImpl;

import com.nibir.medicine_index.model.MedicineModel;
import com.nibir.medicine_index.repository.MedicineRepository;
import com.nibir.medicine_index.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    MedicineRepository medicineRepository;

    @Override
    public ResponseEntity<?> getAllMedicine() {
        return null;
    }

    @Override
    public ResponseEntity<?> updateMedicine(MedicineModel manufactureModel) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteMedicine(Long id) {
        return null;
    }

}

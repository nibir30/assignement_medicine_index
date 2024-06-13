package com.nibir.medicine_index.serviceImpl;

import com.nibir.medicine_index.data.ReqData.AddMedicineReqData;
import com.nibir.medicine_index.model.ManufactureModel;
import com.nibir.medicine_index.model.MedicineModel;
import com.nibir.medicine_index.repository.ManufactureRepository;
import com.nibir.medicine_index.repository.MedicineRepository;
import com.nibir.medicine_index.service.MedicineService;
import com.nibir.medicine_index.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
@Slf4j
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    MedicineRepository medicineRepository;
    @Autowired
    ManufactureRepository manufactureRepository;

    @Override
    public ResponseEntity<?> getAllMedicine() {
        return null;
    }

    @Override
    public ResponseEntity<?> updateMedicine(AddMedicineReqData addMedicineReqData) {
        try {
            MedicineModel medicineModel = new MedicineModel();
            if (addMedicineReqData.getMedicineId() != null) {
                Optional<MedicineModel> medicineModel1 = medicineRepository.findById(addMedicineReqData.getMedicineId());
                if (medicineModel1.isEmpty()) {
                    return ResponseUtils.validationError("Medicine not found");
                } else {
                    medicineModel = medicineModel1.get();
                }
            } else {
                medicineModel.setInsertTime(LocalDateTime.now());
            }
            Optional<ManufactureModel> manufactureModel = manufactureRepository.findById(addMedicineReqData.getManufacturerId());
            if (manufactureModel.isEmpty()) {
                return ResponseUtils.validationError("Manufacturer not found");
            } else {
                medicineModel.setManufacturer(manufactureModel.get());
            }
            medicineModel.setUpdateTime(LocalDateTime.now());
            BeanUtils.copyProperties(addMedicineReqData, medicineModel);
            medicineRepository.save(medicineModel);
            return ResponseUtils.dataSuccess("Successfully saved medicine", medicineModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Successfully saved medicine", e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteMedicine(Long id) {
        return null;
    }

}

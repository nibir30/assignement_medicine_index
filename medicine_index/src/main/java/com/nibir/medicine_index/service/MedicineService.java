package com.nibir.medicine_index.service;

import com.nibir.medicine_index.data.ReqData.AddMedicineReqData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface MedicineService {
    ResponseEntity<?> getAllMedicine();

    ResponseEntity<?> getPaginatedMedicine(int pageNo, int size, String sortBy, String sortType, String search);

    ResponseEntity<?> updateMedicine(MultipartFile medicine_image, AddMedicineReqData manufactureModel);

    ResponseEntity<?> deleteMedicine(Long id);
}

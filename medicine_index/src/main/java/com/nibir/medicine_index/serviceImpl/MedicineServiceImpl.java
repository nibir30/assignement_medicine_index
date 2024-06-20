package com.nibir.medicine_index.serviceImpl;

import com.nibir.medicine_index.data.ReqData.AddMedicineReqData;
import com.nibir.medicine_index.data.ResData.MediaResData;
import com.nibir.medicine_index.data.ResData.core.PaginatedResData;
import com.nibir.medicine_index.data.ResData.core.PaginationBaseData;
import com.nibir.medicine_index.data.ResData.core.PaginationRequestBaseData;
import com.nibir.medicine_index.data.ResData.core.ResponseBaseData;
import com.nibir.medicine_index.model.MedicineModel;
import com.nibir.medicine_index.repository.ManufactureRepository;
import com.nibir.medicine_index.repository.MedicineRepository;
import com.nibir.medicine_index.service.MediaFileService;
import com.nibir.medicine_index.service.MedicineService;
import com.nibir.medicine_index.util.IdGenerator;
import com.nibir.medicine_index.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@Slf4j
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    MedicineRepository medicineRepository;
    @Autowired
    ManufactureRepository manufactureRepository;
    @Autowired
    MediaFileService mediaFileService;

    @Override
    public ResponseEntity<?> getAllMedicine() {
        return null;
    }

    @Override
    public ResponseEntity<?> getSingleMedicine(Long medicineId) {
        try {
            if (medicineId == null) {
                return ResponseUtils.validationError("Invalid medicine!");
            }
            Optional<MedicineModel> medicineModel1 = medicineRepository.findById(medicineId);
            if (medicineModel1.isPresent()) {
                return ResponseUtils.dataSuccess("Successfully found medicine", medicineModel1.get());
            } else {
                return ResponseUtils.validationError("Could not found medicine!");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Could not found medicine!", e.getMessage());
        }
    }


    @Override
    public ResponseEntity<?> getPaginatedMedicine(int pageNo, int size, String sortBy, String sortType, String search) {
        try {
            List<MedicineModel> medicines = new ArrayList<MedicineModel>();
            Pageable paging = PageRequest.of(pageNo, size);

            Page<MedicineModel> pageTuts;

            if (search == null || search.isEmpty()) {
                pageTuts = medicineRepository.findAll(paging);
            } else {
                pageTuts = medicineRepository.findByNameContainingOrGenericNameContaining(search, search, paging);
            }
            medicines = pageTuts.getContent();

            PaginationBaseData<?> paginationBaseData = PaginationBaseData.builder()
                    .currentPage(pageTuts.getNumber())
                    .totalItems(pageTuts.getTotalElements())
                    .totalPages(pageTuts.getTotalPages())
                    .listResponse(medicines)
                    .build();
            PaginationRequestBaseData paginationRequestBaseData = PaginationRequestBaseData.builder()
                    .currentPage(pageNo)
                    .perPage(size)
                    .sortBy(sortBy)
                    .sortType(sortType)
                    .search(search)
                    .build();

            PaginatedResData<?> paginatedResData = PaginatedResData.builder()
                    .request(paginationRequestBaseData)
                    .data(paginationBaseData)
                    .build();

            return ResponseUtils.dataSuccess("Successfully got medicines", paginatedResData);

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error getting medicines", e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateMedicine(MultipartFile medicine_image, AddMedicineReqData addMedicineReqData) {
        try {
            MedicineModel medicineModel = new MedicineModel();

            if (addMedicineReqData.getId() != null) {
                Optional<MedicineModel> medicineModel1 = medicineRepository.findById(addMedicineReqData.getId());
                if (medicineModel1.isEmpty()) {
                    return ResponseUtils.validationError("Medicine not found");
                } else {
                    medicineModel = medicineModel1.get();
                }
            } else {
                medicineModel.setInsertTime(LocalDateTime.now());
                medicineModel.setMedicineId(IdGenerator.generateId());
            }
            if (medicine_image != null) {
                ResponseEntity<?> mediaResponse = mediaFileService.saveMedia(medicine_image);
                Object responseBaseData = mediaResponse.getBody();
                if (responseBaseData != null) {
                    if (responseBaseData.getClass() == ResponseBaseData.class) {
                        ResponseBaseData<?> responseBase = (ResponseBaseData<?>) responseBaseData;
                        Object finalResponse = responseBase.getData();
                        if (finalResponse.getClass() == MediaResData.class) {
                            MediaResData mediaResData = (MediaResData) finalResponse;
                            medicineModel.setImageId(mediaResData.getMediaFileId());
                        }
                    }
                }
            }
//            if (addMedicineReqData.getManufacturerId() != null) {
//                Optional<ManufactureModel> manufactureModel = manufactureRepository.findById(addMedicineReqData.getManufacturerId());
//                if (manufactureModel.isEmpty()) {
//                    return ResponseUtils.validationError("Manufacturer not found");
//                } else {
//                    medicineModel.setManufacturer(manufactureModel.get());
//                }
//            }

            medicineModel.setUpdateTime(LocalDateTime.now());
            BeanUtils.copyProperties(addMedicineReqData, medicineModel);
            medicineRepository.save(medicineModel);
            return ResponseUtils.dataSuccess("Successfully saved medicine", medicineModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error saving medicine", e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteMedicine(Long id) {
        try {
            medicineRepository.deleteById(id);
            return ResponseUtils.success("Successfully deleted medicine");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error deleting medicine", e.getMessage());
        }
    }

}

package com.nibir.medicine_index.controller;

import com.nibir.medicine_index.controller.common.ApiUrlConstants;
import com.nibir.medicine_index.controller.common.PublicApiUrlConstants;
import com.nibir.medicine_index.data.ReqData.AddMedicineReqData;
import com.nibir.medicine_index.service.MedicineService;
import com.nibir.medicine_index.util.ResponseUtils;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @RequestMapping(value = ApiUrlConstants.SAVE_MEDICINE, method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<?> saveMedicine(@Nullable @RequestPart(name = "medicine_image", required = false) MultipartFile medicine_image,
                                          @Valid AddMedicineReqData medicineModel) {
        try {
            return medicineService.updateMedicine(medicine_image, medicineModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error saving medicine", e.getMessage());
        }
    }

    @RequestMapping(value = PublicApiUrlConstants.GET_MEDICINE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAllMedicine() {
        try {
            return medicineService.getAllMedicine();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error getting medicines", e.getMessage());
        }
    }

    @RequestMapping(value = PublicApiUrlConstants.GET_PAGINATED_MEDICINE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getPaginatedMedicine(@RequestParam(value = "perPage", required = false, defaultValue = "10") int size,
                                                  @RequestParam(value = "currentPage", required = false, defaultValue = "0") int pageNo,
                                                  @Nullable @RequestParam(value = "sortBy", required = false) String sortBy,
                                                  @Nullable @RequestParam(value = "sortType", required = false) String sortType,
                                                  @Nullable @RequestParam(value = "search", required = false) String search) {
        try {
            return medicineService.getPaginatedMedicine(pageNo, size, sortBy, sortType, search);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error getting medicines", e.getMessage());
        }
    }

    @RequestMapping(value = ApiUrlConstants.DELETE_MEDICINE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> deleteMedicine(@PathVariable("id") Long id) {
        try {
            return medicineService.deleteMedicine(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error deleting medicine", e.getMessage());
        }
    }
}

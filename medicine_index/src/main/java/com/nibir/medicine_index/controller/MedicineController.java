package com.nibir.medicine_index.controller;

import com.nibir.medicine_index.controller.common.ApiUrlConstants;
import com.nibir.medicine_index.controller.common.PublicApiUrlConstants;
import com.nibir.medicine_index.data.ReqData.AddMedicineReqData;
import com.nibir.medicine_index.service.MedicineService;
import com.nibir.medicine_index.util.ResponseUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @RequestMapping(value = ApiUrlConstants.SAVE_MEDICINE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> saveMedicine(@RequestBody @Valid AddMedicineReqData medicineModel) {
        try {
            return medicineService.updateMedicine(medicineModel);
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

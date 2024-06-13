package com.nibir.medicine_index.controller;

import com.nibir.medicine_index.controller.common.ApiUrlConstants;
import com.nibir.medicine_index.controller.common.PublicApiUrlConstants;
import com.nibir.medicine_index.model.ManufactureModel;
import com.nibir.medicine_index.service.ManufactureService;
import com.nibir.medicine_index.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ManufactureController {

    @Autowired
    private ManufactureService manufactureService;

    @RequestMapping(value = ApiUrlConstants.SAVE_MANUFACTURER, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> saveManufacturer(@RequestBody ManufactureModel manufactureModel) {
        try {
            return manufactureService.updateManufacturer(manufactureModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error saving manufacturer", e.getMessage());
        }
    }

    @RequestMapping(value = PublicApiUrlConstants.GET_MANUFACTURER, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAllManufacturer() {
        try {
            return manufactureService.getAllManufacturer();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error getting manufacturers", e.getMessage());
        }
    }

    @RequestMapping(value = ApiUrlConstants.DELETE_MANUFACTURER, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> deleteManufacturer(@PathVariable("id") Long id) {
        try {
            return manufactureService.deleteManufacturer(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Error deleting manufacturer", e.getMessage());
        }
    }
}

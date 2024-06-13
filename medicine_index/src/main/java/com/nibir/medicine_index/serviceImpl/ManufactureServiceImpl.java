package com.nibir.medicine_index.serviceImpl;

import com.nibir.medicine_index.model.ManufactureModel;
import com.nibir.medicine_index.repository.ManufactureRepository;
import com.nibir.medicine_index.service.ManufactureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
public class ManufactureServiceImpl implements ManufactureService {
    @Autowired
    ManufactureRepository manufactureRepository;

    @Override
    public ResponseEntity<?> getAllManufacturer() {
        return null;
    }

    @Override
    public ResponseEntity<?> updateManufacturer(ManufactureModel manufactureModel) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteManufacturer(Long id) {
        return null;
    }


}

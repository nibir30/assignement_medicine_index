package com.nibir.medicine_index.repository;

import com.nibir.medicine_index.model.MedicineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<MedicineModel, Long> {
}

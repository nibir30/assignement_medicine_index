package com.nibir.medicine_index.repository;

import com.nibir.medicine_index.model.ManufactureModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufactureRepository extends JpaRepository<ManufactureModel, Long> {
}

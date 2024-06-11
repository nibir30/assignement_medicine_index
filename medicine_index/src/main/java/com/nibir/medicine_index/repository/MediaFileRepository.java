package com.nibir.medicine_index.repository;

import com.nibir.medicine_index.model.MediaFileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaFileRepository extends JpaRepository<MediaFileModel, Long> {

}

package com.nibir.medicine_index.repository;

import com.nibir.medicine_index.model.LookupMediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookupMediaTypeRepository extends JpaRepository<LookupMediaType, Long> {
    LookupMediaType findByMediaTypeName(String mediaTypeName);
}

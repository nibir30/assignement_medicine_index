package com.nibir.medicine_index.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "medicine")
public class MedicineModel {
    @Id
    private Long medicineId;
    private Long imageId;
    private String name;
    private String genericName;
    private String price;
    private String batchNo;
    private String otherDetails;

    //    @ManyToOne(targetEntity = ManufactureModel.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//            CascadeType.DETACH})
//    private ManufactureModel manufacturer;
    private String manufacturer;

    @JsonIgnore
    private LocalDateTime insertTime;
    @JsonIgnore
    private LocalDateTime updateTime;
}

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
@Entity(name = "manufacture")
public class ManufactureModel {
    @Id
    private Long manufactureId;
    private String name;
    private String imageId;
    private String description;
    private String startedSince;

    @JsonIgnore
    private LocalDateTime insertTime;
    @JsonIgnore
    private LocalDateTime updateTime;
}

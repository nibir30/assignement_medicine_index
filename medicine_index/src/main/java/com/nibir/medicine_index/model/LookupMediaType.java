package com.nibir.medicine_index.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "lMediaType")
public class LookupMediaType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaTypeId;
    private String mediaTypeName;
    private Long maximumSize;
}
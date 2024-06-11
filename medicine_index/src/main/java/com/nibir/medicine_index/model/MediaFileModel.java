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
@Entity(name = "mediaFiles")
public class MediaFileModel {
    @JsonIgnore
    LocalDateTime insertTime;
    @JsonIgnore
    LocalDateTime updateTime;
    @Id
    private Long mediaFileId;
    private String mediaFileType;
    private String mediaFileName;
    private String filePath;
    private String fileSize;
    private String thumbnailFilePath;
}


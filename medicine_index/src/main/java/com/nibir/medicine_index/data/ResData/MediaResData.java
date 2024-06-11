package com.nibir.medicine_index.data.ResData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MediaResData {
    private Long mediaFileId;
    private String mediaFileType;
    private String mediaFileName;
    private String filePath;
    private String fileSize;
//    private String thumbnailFilePath;
}

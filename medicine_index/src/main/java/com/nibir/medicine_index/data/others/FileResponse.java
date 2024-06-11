package com.nibir.medicine_index.data.others;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileResponse {
    private boolean isDataAvailable;
    private String fileType;
    private byte[] byteData;
}

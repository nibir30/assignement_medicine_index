package com.nibir.medicine_index.service;


import com.nibir.medicine_index.data.others.FileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface MediaFileService {
    ResponseEntity<?> saveMedia(MultipartFile file);

//    ResponseEntity<?> getMediaById(Long id, Long userId);

    ResponseEntity<?> getAllFileInfo();

    FileResponse getFile(Long mediaId);

    FileResponse getThumbnail(Long mediaId);
}

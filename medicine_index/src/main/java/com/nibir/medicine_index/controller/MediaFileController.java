package com.nibir.medicine_index.controller;

import com.nibir.medicine_index.controller.common.PublicApiUrlConstants;
import com.nibir.medicine_index.data.others.FileResponse;
import com.nibir.medicine_index.service.MediaFileService;
import com.nibir.medicine_index.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MediaFileController {

    @Autowired
    MediaFileService mediaFileService;


    @PostMapping(path = PublicApiUrlConstants.SAVE_MEDIA, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<?> addMedia(@RequestParam("media") MultipartFile file, Authentication authentication) {
        try {
            return mediaFileService.saveMedia(file);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseUtils.exceptionError("Unable to save media", ex.getMessage());
        }
    }

    @GetMapping(path = PublicApiUrlConstants.MEDIA)
    @ResponseBody
    public ResponseEntity<?> getImage(@PathVariable Long id) {
        try {
            FileResponse fileResponse = mediaFileService.getFile(id);

            return getResponseEntity(fileResponse.getFileType(), fileResponse.getByteData());
        } catch (Exception e) {
            System.out.println("Error in MediaFileController.getImage()" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private ResponseEntity<?> getResponseEntity(@PathVariable String fileType, byte[] byteData) {
        try {
            if (byteData != null) {
                //based on the media type return file
                if (fileType.equalsIgnoreCase("image")) {
                    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(byteData);
                } else if (fileType.equalsIgnoreCase("video")) {
                    return ResponseEntity.ok().contentType(MediaType.valueOf("video/mp4")).body(byteData);
                } else if (fileType.equalsIgnoreCase("audio")) {
                    return ResponseEntity.ok().contentType(MediaType.valueOf("audio/mp3")).body(byteData);
                } else if (fileType.equalsIgnoreCase("pdf")) {
                    return ResponseEntity.ok().contentType(MediaType.valueOf("application/pdf")).body(byteData);
                } else if (fileType.equalsIgnoreCase("doc")) {
                    return ResponseEntity.ok().contentType(MediaType.valueOf("application/msword")).body(byteData);
                } else if (fileType.equalsIgnoreCase("docx")) {
                    return ResponseEntity.ok().contentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document")).body(byteData);
                } else if (fileType.equalsIgnoreCase("xls")) {
                    return ResponseEntity.ok().contentType(MediaType.valueOf("application/vnd.ms-excel")).body(byteData);
                } else if (fileType.equalsIgnoreCase("xlsx")) {
                    return ResponseEntity.ok().contentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")).body(byteData);
                } else if (fileType.equalsIgnoreCase("ppt")) {
                    return ResponseEntity.ok().contentType(MediaType.valueOf("application/vnd.ms-powerpoint")).body(byteData);
                } else if (fileType.equalsIgnoreCase("pptx")) {
                    return ResponseEntity.ok().contentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.presentationml.presentation")).body(byteData);
                } else if (fileType.equalsIgnoreCase("txt")) {
                    return ResponseEntity.ok().contentType(MediaType.valueOf("text/plain")).body(byteData);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println("Error in MediaFileController.getResponseEntity()" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}

package com.nibir.medicine_index.serviceImpl;

import com.nibir.medicine_index.data.ResData.MediaResData;
import com.nibir.medicine_index.data.others.FileResponse;
import com.nibir.medicine_index.exception.FileUploadException;
import com.nibir.medicine_index.model.LookupMediaType;
import com.nibir.medicine_index.model.MediaFileModel;
import com.nibir.medicine_index.repository.LookupMediaTypeRepository;
import com.nibir.medicine_index.repository.MediaFileRepository;
import com.nibir.medicine_index.repository.UserRepository;
import com.nibir.medicine_index.service.MediaFileService;
import com.nibir.medicine_index.util.IdGenerator;
import com.nibir.medicine_index.util.ResponseUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MediaFileServiceImpl implements MediaFileService {
    public final String path = "/media/";

    @Autowired
    MediaFileRepository mediaFileRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LookupMediaTypeRepository lookupMediaTypeRepository;

    @Value("${resources.path}")
    String projectPath;

    @Override
    public ResponseEntity<?> saveMedia(MultipartFile file) {
        String fileName = "";
        LocalDateTime time = LocalDateTime.now();
        String year = String.valueOf(time.getYear());
        try {
            String fileSizeInKb = String.valueOf(file.getSize() / 1024);

            String fileType = file.getContentType();

            String mediaFileType = "";

            //Manipulate original file name
            String originalFileName = file.getOriginalFilename();
            assert originalFileName != null;
            String[] originalFileNameArray = originalFileName.split("\\.");
//            String originalFileNameWithoutExtension = originalFileNameArray[0];
            String originalFileExtension = originalFileNameArray[1];
            Long mediaId = IdGenerator.generateId();
            String manipulatedFileName = mediaId + "." + originalFileExtension;

            assert fileType != null;
            if (fileType.contains("image")) {
                mediaFileType = "image";
            } else if (fileType.contains("video")) {
                mediaFileType = "video";
            } else if (fileType.contains("audio")) {
                mediaFileType = "audio";
            } else if (fileType.contains("pdf")) {
                mediaFileType = "pdf";
            } else if (fileType.contains("text")) {
                mediaFileType = "txt";
            } else {
                mediaFileType = "other";
            }

            /*Path root = Paths.get("D:" + File.separator + "sbc_resources" + File.separator + "media" + File.separator
                    + mediaFileType + File.separator + year);*/
            //this time we will create directory in the project root directory
            Path root = Paths.get(projectPath + File.separator + "media" + File.separator + mediaFileType + File.separator + year);
            if (!Files.exists(root)) {
                initDirectory(mediaFileType);
            }
//            FileSizeResponse fileSizeResponse = isFileSizeValid(fileSizeInKb, mediaFileType);
//            if (!fileSizeResponse.isValid()) {
//                return ResponseUtils.validationError("Maximum file size - " + fileSizeResponse.getMaxSize() + "KB exceeded.");
//            }

            Files.copy(file.getInputStream(), root.resolve(manipulatedFileName), StandardCopyOption.REPLACE_EXISTING);

            fileName = path + mediaFileType + "/" + year + "/" + manipulatedFileName;
            // Get the file type, like image, video, audio, txt, pdf etc.

            String thumbnail_path = "";

            if (mediaFileType.equals("image")) {
                Path thumbnailRoot = Paths.get(projectPath + File.separator + "media" + File.separator + mediaFileType + File.separator + year + File.separator + "thumbnail");
                if (!Files.exists(thumbnailRoot)) {
                    initThumbnailDirectory(mediaFileType);
                }

                saveScaledImage(root.resolve(manipulatedFileName).toString(), thumbnailRoot.resolve(manipulatedFileName).toString());
                thumbnail_path = path + mediaFileType + "/" + year + "/thumbnail/" + manipulatedFileName;

            } else {
                thumbnail_path = fileName;
            }


            MediaFileModel mediaFile = MediaFileModel.builder()
                    .mediaFileId(mediaId)
                    .mediaFileName(file.getOriginalFilename())
                    .fileSize(fileSizeInKb)
                    .mediaFileType(mediaFileType)
                    .filePath(fileName)
                    .thumbnailFilePath(thumbnail_path)
                    .insertTime(LocalDateTime.now())
                    .build();

            MediaFileModel savedMediaFile = mediaFileRepository.save(mediaFile);

            MediaResData mediaResData = new MediaResData();

            BeanUtils.copyProperties(savedMediaFile, mediaResData);
            return ResponseUtils.dataSuccess("File Saved Successfully!", mediaResData);

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.exceptionError("Could not store the file.", e.getMessage());
        }
    }

    @Override
    public FileResponse getFile(Long mediaId) {
        try {
            Optional<MediaFileModel> mediaFileModel = mediaFileRepository.findById(mediaId);
            if (mediaFileModel.isEmpty()) {
                return null;
            }
//            String fileType, String year, String fileName
            Path filePath = Paths.get(projectPath, mediaFileModel.get().getFilePath());

            // Check if the file exists
            if (!Files.exists(filePath)) {
                System.err.println("File not found: " + filePath.toString());
                return FileResponse.builder()
                        .isDataAvailable(false)
                        .build();
            } else if (!Files.isRegularFile(filePath)) {
                // Check if it's a file
                System.err.println("Not a regular file: " + filePath.toString());
                return FileResponse.builder()
                        .isDataAvailable(false)
                        .build();
            } else if (!Files.isReadable(filePath)) {
                // Check if it's readable
                System.err.println("File is not readable: " + filePath.toString());
                return FileResponse.builder()
                        .isDataAvailable(false)
                        .build();
            }
            return FileResponse.builder()
                    .isDataAvailable(true)
                    .fileType(mediaFileModel.get().getMediaFileType())
                    .byteData(Files.readAllBytes(filePath))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllFileInfo() {
        List<MediaResData> mediaResDataList = new ArrayList<>();
        List<MediaFileModel> mediaFileModelList = mediaFileRepository.findAll();

        for (MediaFileModel media : mediaFileModelList) {
            MediaResData mediaResData = new MediaResData();
            BeanUtils.copyProperties(media, mediaResData);
            mediaResDataList.add(mediaResData);
        }
        return ResponseUtils.dataSuccess("success", mediaResDataList);
    }


    @Override
    public FileResponse getThumbnail(Long mediaId) {
        try {
            Optional<MediaFileModel> mediaFileModel = mediaFileRepository.findById(mediaId);
            if (mediaFileModel.isEmpty()) {
                return null;
            }
            Path filePath = Paths.get(projectPath, mediaFileModel.get().getThumbnailFilePath());

            // Check if the file exists
            if (!Files.exists(filePath)) {
                System.err.println("File not found: " + filePath.toString());
                return FileResponse.builder()
                        .isDataAvailable(false)
                        .build();
            } else if (!Files.isRegularFile(filePath)) {
                System.err.println("Not a regular file: " + filePath.toString());
                return FileResponse.builder()
                        .isDataAvailable(false)
                        .build();
            } else if (!Files.isReadable(filePath)) {
                System.err.println("File is not readable: " + filePath.toString());
                return FileResponse.builder()
                        .isDataAvailable(false)
                        .build();
            }

            return FileResponse.builder()
                    .isDataAvailable(true)
                    .fileType(mediaFileModel.get().getMediaFileType())
                    .byteData(Files.readAllBytes(filePath))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void initDirectory(String fileType) {
        try {
            String year = String.valueOf(LocalDateTime.now().getYear());
            Files.createDirectories(Paths.get(projectPath + File.separator + "media" + File.separator + fileType + File.separator + year));
        } catch (IOException e) {
//            return ResponseUtils.exceptionError("Could not create upload folder!", e.getMessage());
            throw new FileUploadException("Could not create upload folder!");
        }
    }

    private FileSizeResponse isFileSizeValid(String fileSize, String fileType) {

        LookupMediaType mediaType = lookupMediaTypeRepository.findByMediaTypeName(fileType);

        if (mediaType == null) {
            return FileSizeResponse.builder()
                    .isValid(false)
                    .build();
        }

        if (mediaType.getMaximumSize() < Long.parseLong(fileSize)) {
            return FileSizeResponse.builder()
                    .isValid(false)
                    .maxSize(mediaType.getMaximumSize())
                    .build();
        }
        return FileSizeResponse.builder()
                .isValid(true)
                .build();
    }

    public void initThumbnailDirectory(String fileType) {
        try {
            String year = String.valueOf(LocalDateTime.now().getYear());
            Files.createDirectories(Paths.get(projectPath + File.separator + "media" + File.separator + fileType + File.separator + year + File.separator + "thumbnail"));
        } catch (IOException e) {
            throw new FileUploadException("Could not create upload folder!");
        }
    }

    private void saveScaledImage(String filePath, String outputFile) {
        try {

            BufferedImage sourceImage = ImageIO.read(new File(filePath));
            int width = sourceImage.getWidth();
            int height = sourceImage.getHeight();

            if (width > height) {
                float extraSize = height - 200;
                float percentHeight = (extraSize / height) * 200;
                float percentWidth = width - (((float) width / 200) * percentHeight);
                BufferedImage img = new BufferedImage((int) percentWidth, 200, BufferedImage.TYPE_INT_RGB);
                Image scaledImage = sourceImage.getScaledInstance((int) percentWidth, 200, Image.SCALE_SMOOTH);
                img.createGraphics().drawImage(scaledImage, 0, 0, null);
                new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
                BufferedImage img2;
                img2 = img.getSubimage((int) ((percentWidth - 200) / 2), 0, 200, 200);
                String formatName = outputFile.substring(outputFile.lastIndexOf(".") + 1);
                ImageIO.write(img2, formatName, new File(outputFile));
            } else {
                float extraSize = width - 200;
                float percentWidth = (extraSize / width) * 200;
                float percentHeight = height - (((float) height / 200) * percentWidth);
                BufferedImage img = new BufferedImage(200, (int) percentHeight, BufferedImage.TYPE_INT_RGB);
                Image scaledImage = sourceImage.getScaledInstance(200, (int) percentHeight, Image.SCALE_SMOOTH);
                img.createGraphics().drawImage(scaledImage, 0, 0, null);
                new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
                BufferedImage img2;
                img2 = img.getSubimage(0, (int) ((percentHeight - 200) / 2), 200, 200);
                String formatName = outputFile.substring(outputFile.lastIndexOf(".") + 1);
                ImageIO.write(img2, formatName, new File(outputFile));
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
class FileSizeResponse {
    private Long maxSize;
    private boolean isValid;
}


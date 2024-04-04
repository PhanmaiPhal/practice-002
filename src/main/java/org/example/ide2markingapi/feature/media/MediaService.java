package org.example.ide2markingapi.feature.media;

import org.example.ide2markingapi.feature.media.dto.MediaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    MediaResponse uploadingSingle(MultipartFile file,String folderName);

    List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName);

    MediaResponse loadMediaByName (String mediaName,String folderName);

    MediaResponse deleteMediaByName(String mediaName, String folderName);

    List<MediaResponse> getAllMedia(String images);

    ResponseEntity downloadMediaByName(String name, String images);
}

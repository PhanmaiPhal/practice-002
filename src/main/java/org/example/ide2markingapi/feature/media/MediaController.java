package org.example.ide2markingapi.feature.media;

import lombok.RequiredArgsConstructor;
import org.example.ide2markingapi.feature.media.dto.MediaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/medias")
public class MediaController {

    private final MediaService mediaService;

    @PostMapping("/upload-single")
    MediaResponse uploadingSingle(@RequestPart MultipartFile file){
        return mediaService.uploadingSingle(file, "images");
    }
    @PostMapping("/upload-multiple")
    List<MediaResponse> uploadMultiple (@RequestPart List<MultipartFile> files){
        return mediaService.uploadMultiple(files,"images");
    }

    @GetMapping("/{mediaName}")
    MediaResponse loadMediaByName(@PathVariable String mediaName){
        return mediaService.loadMediaByName(mediaName,"images");
    }

    @DeleteMapping("/{mediaName}")
    MediaResponse deleteMediaByName(@PathVariable String mediaName){
        return mediaService.deleteMediaByName(mediaName,"images");
    }

    @GetMapping
    List<MediaResponse> findAllMedia(){
        return mediaService.getAllMedia("images");
    }

    @GetMapping("/download/{name}")
    ResponseEntity downloadByName(@PathVariable String name){
        return mediaService.downloadMediaByName(name, "images");
    }
}

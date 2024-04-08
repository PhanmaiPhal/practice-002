package org.example.ide2markingapi.exection;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice
public class MediaLoadException {

//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    ResponseEntity<?> handleMaxUpLoadSizeException(MaxUploadSizeExceededException)

//    @Value("${spring.servlet}")

}

package org.example.ide2markingapi.exection;

import org.example.ide2markingapi.base.BasedError;
import org.example.ide2markingapi.base.BasedErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BasedErrorResponse handleValidationError(MethodArgumentNotValidException ex){
        BasedError<List<?>> basedError = new BasedError<>();
        List<Map<String, Object>> errorList = new ArrayList<>();
        ex.getFieldErrors().stream()
                .forEach(fieldError -> {
                    Map<String,Object> error = new HashMap<>();
                    error.put("field",fieldError.getField());
                    error.put("message",fieldError.getDefaultMessage());
                    errorList.add(error);
                });
        basedError.setCode(HttpStatus.BAD_GATEWAY.getReasonPhrase());
        basedError.setDescription(errorList);
        return new BasedErrorResponse(basedError);
    }
}

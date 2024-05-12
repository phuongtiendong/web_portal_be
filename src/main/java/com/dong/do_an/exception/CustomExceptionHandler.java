package com.dong.do_an.exception;

import com.dong.do_an.constants.StatusCode;
import com.dong.do_an.model.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleAllException(Exception ex) {
        return ResponseEntity
                .ok()
                .body(
                        BaseResponse
                                .builder()
                                .code(StatusCode.SERVER_ERROR)
                                .build()
                );
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity handleAccessDeniedException() {
        return ResponseEntity
                .ok()
                .body(
                        BaseResponse
                                .builder()
                                .code(StatusCode.NO_PERMISSION)
                                .build()
                );
    }
}

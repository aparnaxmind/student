package com.student.demo.Exception.handler;

import com.student.demo.Exception.ApiException;
import com.student.demo.Exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {StudentNotFoundException.class})
    public ResponseEntity<Object> studentNotFoundException(StudentNotFoundException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException=new ApiException(ex.getMessage(),HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}

package com.vukat.studentresource.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleCourseNotFoundException(CourseNotFoundException ex, WebRequest request){
        CourseNotFoundExceptionResponse response = new CourseNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameAlreadyExistException(UsernameAlreadyExistException ex,WebRequest request){

        UsernameAlreadyExistResponse response = new UsernameAlreadyExistResponse(ex.getMessage());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex,WebRequest request){

        ResourceNotFoundExceptionResponse response = new ResourceNotFoundExceptionResponse(ex.getMessage());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }
}


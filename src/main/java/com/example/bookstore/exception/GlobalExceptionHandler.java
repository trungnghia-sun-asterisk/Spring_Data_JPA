package com.example.bookstore.exception;
import org.springframework.dao.DataIntegrityViolationException; import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import org.springframework.http.converter.HttpMessageNotReadableException; import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) ResponseEntity<ErrorResponse> notFound(ResourceNotFoundException e){return response(HttpStatus.NOT_FOUND,e.getMessage(),Map.of());}
    @ExceptionHandler(ConflictException.class) ResponseEntity<ErrorResponse> conflict(ConflictException e){return response(HttpStatus.CONFLICT,e.getMessage(),Map.of());}
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException e){Map<String,String> errors=new LinkedHashMap<>(); e.getBindingResult().getFieldErrors().forEach(x->errors.putIfAbsent(x.getField(),x.getDefaultMessage())); return response(HttpStatus.BAD_REQUEST,"Validation failed",errors);}
    @ExceptionHandler({DataIntegrityViolationException.class,HttpMessageNotReadableException.class}) ResponseEntity<ErrorResponse> badRequest(Exception e){return response(HttpStatus.BAD_REQUEST,"Request could not be processed",Map.of());}
    private ResponseEntity<ErrorResponse> response(HttpStatus s,String m,Map<String,String> errors){return ResponseEntity.status(s).body(new ErrorResponse(s.value(),m,errors));}
    public record ErrorResponse(int status,String message,Map<String,String> errors){}
}

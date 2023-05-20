package com.bloggingApp.exception;

import java.util.Map; 
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bloggingApp.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler (ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler (ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		
	}

	@ExceptionHandler  (MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler (MethodArgumentNotValidException ex){
		
		Map<String, String> validationErrors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((errors) -> {
			String fieldName = ((FieldError) errors).getField();
			String message = errors.getDefaultMessage();
			validationErrors.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(validationErrors, HttpStatus.BAD_REQUEST);
		
	}
	
	
}

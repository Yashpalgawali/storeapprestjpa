package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	// This will handle all the exceptions related t Internal_server_error
	@ExceptionHandler
	public ResponseEntity<ErrorResponseDto> handleGlobalExceptions(Exception ex, WebRequest request) {
		ErrorResponseDto errorDto = new ErrorResponseDto(
						request.getDescription(false),
						HttpStatus.INTERNAL_SERVER_ERROR,
						ex.getMessage(),
						LocalDateTime.now()
			);
		
		return new ResponseEntity<ErrorResponseDto>(errorDto , HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorResponseDto errorDto = new ErrorResponseDto(
						request.getDescription(false),
						HttpStatus.NOT_FOUND,
						ex.getMessage(),
						LocalDateTime.now()
			);
		
		return new ResponseEntity<ErrorResponseDto>(errorDto , HttpStatus.NOT_FOUND);
	}
}

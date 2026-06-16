package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

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
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorResponseDto errorDto = new ErrorResponseDto(
						request.getDescription(false),
						HttpStatus.NOT_FOUND,
						ex.getMessage(),
						LocalDateTime.now()
			);
		
		return new ResponseEntity<ErrorResponseDto>(errorDto , HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex, WebRequest request) {
		ErrorResponseDto errorDto = new ErrorResponseDto(
						request.getDescription(false),
						HttpStatus.BAD_GATEWAY,
						ex.getMessage(),
						LocalDateTime.now()
			);
		
		return new ResponseEntity<ErrorResponseDto>(errorDto , HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(ResourceNotModifiedException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotModifiedException(ResourceNotModifiedException ex, WebRequest request) {
		ErrorResponseDto errorDto = new ErrorResponseDto(
						request.getDescription(false),
						HttpStatus.CONFLICT,
						ex.getMessage(),
						LocalDateTime.now()
			);
		
		return new ResponseEntity<ErrorResponseDto>(errorDto , HttpStatus.CONFLICT);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String , String> validationErrors = new HashMap<>();
		List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
		
		validationErrorList.forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String validationMsg = error.getDefaultMessage();
			validationErrors.put(fieldName, validationMsg);
			
		});

		return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
	}
}

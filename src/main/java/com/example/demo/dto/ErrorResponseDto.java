package com.example.demo.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ErrorResponseDto {
	
	@Schema(description = "API path invoked by Customer")
	private String apiPath;
	
	@Schema(description = "This represents the Error Code ")
	private HttpStatus errorCode;
	
	@Schema(description = "This returns the Error Message ")
	private String errorMessage;
	
	@Schema(description = "This represents the time when the Error occured")
	private LocalDateTime error_time;
}

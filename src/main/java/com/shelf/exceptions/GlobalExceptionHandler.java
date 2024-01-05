package com.shelf.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shelf.payloads.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e){
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus("FAILED");
		errorResponse.setErrorMessage(e.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

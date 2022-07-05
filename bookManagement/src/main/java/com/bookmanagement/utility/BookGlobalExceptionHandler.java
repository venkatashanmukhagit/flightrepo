package com.bookmanagement.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookGlobalExceptionHandler {
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ExceptionDetails> handleException(BookNotFoundException e) {
		return new ResponseEntity<>(new ExceptionDetails(e.getMessage(), e.getClass().toString()),HttpStatus.OK);
		
	}

}

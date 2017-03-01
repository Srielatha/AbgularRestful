package com.app.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingAdvice {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public @ResponseBody ResponseEntity<Error> handleValidationException(MethodArgumentNotValidException me) {
		Error error = new Error();
		me.getBindingResult().getAllErrors().forEach(e -> error.getMessages().add(e.getDefaultMessage()));
		error.setCause("Request Failed - " + error.getMessages().size()
				+ (error.getMessages().size() > 1 ? "errors" : "error"));
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(value = Exception.class)
	public @ResponseBody Error handleGeneralExceptions(Exception me) {
		Error error = new Error();
		error.setCause(me.getMessage());
		return error;
	}

}
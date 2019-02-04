package com.tlabs.budget.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BudgetAppExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<BudgetAppResponse> handleException(Exception exception) {

		BudgetAppResponse error = new BudgetAppResponse();

		error.setMessage(exception.getMessage());
		error.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);

	}

}

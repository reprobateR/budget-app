package com.tlabs.budget.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tlabs.budget.app.exception.BudgetAppErrorResponse;

@ControllerAdvice
public class BudgetAppGlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<BudgetAppErrorResponse> handleException(Exception exception) {

		BudgetAppErrorResponse error = new BudgetAppErrorResponse();

		error.setMessage(exception.getMessage());
		error.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);

	}

}

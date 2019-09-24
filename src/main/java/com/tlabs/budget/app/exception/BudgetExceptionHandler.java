package com.tlabs.budget.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BudgetExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<BudgetErrorResponse> handleException(RequestException requestException){

		BudgetErrorResponse budgetErrorResponse = new BudgetErrorResponse();
		budgetErrorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
		budgetErrorResponse.setMessage(requestException.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(budgetErrorResponse);
	}

}

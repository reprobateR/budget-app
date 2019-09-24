package com.tlabs.budget.app.exception;

import org.springframework.http.HttpStatus;

public class BudgetErrorResponse {
	
	private String message;
	private HttpStatus httpStatus;

	public BudgetErrorResponse(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public BudgetErrorResponse() {
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
	

}

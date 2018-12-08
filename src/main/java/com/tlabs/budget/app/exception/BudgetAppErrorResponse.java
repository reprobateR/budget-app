package com.tlabs.budget.app.exception;

import org.springframework.http.HttpStatus;

public class BudgetAppErrorResponse {
	
	private String message;
	private HttpStatus httpStatus;
	private Long timestamp;
	
	public BudgetAppErrorResponse() {
		
	}

	public BudgetAppErrorResponse(String message, HttpStatus httpStatus, Long timestamp) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
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


	public Long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}


	@Override
	public String toString() {
		return "BudgetAppErrorResponse [message=" + message + ", httpStatus=" + httpStatus + ", timestamp=" + timestamp
				+ "]";
	}
	
}

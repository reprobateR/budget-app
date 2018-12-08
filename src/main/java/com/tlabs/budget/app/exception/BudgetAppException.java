package com.tlabs.budget.app.exception;

public class BudgetAppException extends Exception {
	/**
	 * Serialization Number
	 */
	private static final long serialVersionUID = -6983108806998283272L;
	
	@SuppressWarnings(value = { "unused" })
	private String message;
	@SuppressWarnings(value = { "unused" })
	private Throwable cause;

	public BudgetAppException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
		this.cause = cause;
	}

	public BudgetAppException(String message) {
		super(message);
		this.message = message;
	}

	public BudgetAppException(Throwable cause) {
		super(cause);
		this.cause = cause;
	}
}

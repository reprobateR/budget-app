package com.tlabs.budget.app.exception;

public class RequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4547875301404031244L;

	public RequestException() {
		super();
	}

	public RequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestException(String message) {
		super(message);
	}

	public RequestException(Throwable cause) {
		super(cause);
	}
	
}

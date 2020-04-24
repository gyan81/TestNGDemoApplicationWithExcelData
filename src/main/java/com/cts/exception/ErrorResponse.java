package com.cts.exception;

import java.util.List;

/**
 * EroorResponse need to be declared in a method or constructor's {@code throws}
 * clause if they can be thrown by the execution of the method or constructor
 * and propagate outside the method or constructor boundary.
 * 
 * @author
 *
 */
public class ErrorResponse {

	private String message;
	private List<String> details;

	/**
	 * Constructs a new ErrorResponse with {@code null} as its detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 */
	public ErrorResponse() {
	}

	/**
	 * Parameterized Constructs a new ErrorResponse with as its detail message.
	 * 
	 * @param message
	 * @param details
	 */
	public ErrorResponse(String message, List<String> details) {
		this.message = message;
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}

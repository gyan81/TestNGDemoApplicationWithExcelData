package com.cts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Unchecked exceptions do <em>not</em> need to be declared in a method or
 * constructor's {@code throws} clause if they can be thrown by the execution of
 * the method or constructor and propagate outside the method or constructor
 * boundary.
 * 
 * @author 788599
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later
	 *            retrieval by the method.
	 */
	public RecordNotFoundException(String message) {
		super(message);
	}

}

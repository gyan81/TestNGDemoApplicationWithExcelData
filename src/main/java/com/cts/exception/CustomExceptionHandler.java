package com.cts.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * A convenient CustomExceptionHandler class for
 * {@link ControllerAdvice @ControllerAdvice} classes that wish to provide
 * centralized exception handling across all {@code @RequestMapping} methods
 * through {@code @ExceptionHandler} methods.
 * 
 * @author 788599
 *
 */

@ControllerAdvice
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * @param exception
	 *            handling for standard exceptions.
	 * @param request
	 *            the current request
	 * @return Create a new ResponseEntity with the given body and status code.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Server exception ocurred", details);
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 
	 * @param exception
	 *            handling for RecordNotFoundException.
	 * @param request
	 *            the current request
	 * @return Create a new ResponseEntity with the given body and status code.
	 */
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("No record found", details);
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);

	}

	/**
	 * 
	 * @param exception
	 *            handling for InternalServerError.
	 * @param request
	 *            the current request
	 * @return Create a new ResponseEntity with the given body and status code.
	 */
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<Object> handleInternalServerException(InternalServerError exception, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Record not found", details);
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : exception.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse("alidation failed", details);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
}

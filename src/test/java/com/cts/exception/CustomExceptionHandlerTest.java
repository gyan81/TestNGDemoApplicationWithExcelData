package com.cts.exception;

import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RunWith(SpringRunner.class)
public class CustomExceptionHandlerTest extends ResponseEntityExceptionHandler {

	@Mock
	WebRequest webRequest;

	@Mock
	BindingResult br;
	@Mock
	public CustomExceptionHandler customExceptionHandler;
	@Mock
	MethodArgumentNotValidException methodArgumentNotValidException;

	ResponseEntity<Object> mockResponse;

	@Before
	public void setUp() {
		mockResponse = null;
		customExceptionHandler = new CustomExceptionHandler();
	}

	@Test
	public void handleAllExceptionTest() {
		ResponseEntity<Object> res = customExceptionHandler.handleAllException(new Exception(), webRequest);

		assertNotNull(res);
	}

	@Test
	public void handleInternalServerExceptionTest() {
		ResponseEntity<Object> res = customExceptionHandler
				.handleInternalServerException(new InternalServerError("InternalServerException"), webRequest);

		assertNotNull(res);
	}

	@Test
	public void handleRecordNotFoundExceptionTest() {
		ResponseEntity<Object> res = customExceptionHandler
				.handleRecordNotFoundException(new RecordNotFoundException("RecordNotFoundException"), webRequest);

		assertNotNull(res);
	}

	@Test
	public void methodArgumentNotValidTest() {

		List<ObjectError> errors = new ArrayList<>();
		errors.add(new ObjectError("", ""));
		Mockito.doReturn(br).when(methodArgumentNotValidException).getBindingResult();
		Mockito.doReturn(errors).when(br).getAllErrors();

		ResponseEntity<Object> res = customExceptionHandler.handleMethodArgumentNotValid(
				methodArgumentNotValidException, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, webRequest);
		assertNotNull(res);

	}

}

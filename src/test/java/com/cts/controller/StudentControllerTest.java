package com.cts.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.model.Student;
import com.cts.service.impl.StudentServiceImpl;

public class StudentControllerTest extends AbstractTest {
	
	@InjectMocks
	StudentController studentController;

	@Mock
	private StudentServiceImpl studentService;
	
	@Before
	@Override
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void testCreateStudentSuccess() throws Exception {
		Student student = getTestStudent();
		when(studentService.createStudent(student)).thenReturn(student);
		ResponseEntity<Object> response = studentController.createStudent(student);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}

	private Student getTestStudent() {
		Student student = new Student();
		student.setId(102);
		student.setName("Bhaskar");
		student.setGrade("A");
		student.setAge(35);
		return student;
	}

}

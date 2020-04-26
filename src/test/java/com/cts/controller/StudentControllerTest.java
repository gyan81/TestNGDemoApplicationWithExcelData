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
	
	private String filePath = "./src/main/resources/excel/students.xlsx";
	
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
	
	@Test
	public void testsearchStudentById()  throws Exception {
		Student student = getTestStudent();
		
		when(studentService.searchStudentById(102)).thenReturn(student);
		ResponseEntity<Object> response = studentController.searchStudentById(102);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}
	
	@Test
	public void testremoveStudent() {
		Student student = getTestStudent();
		when(studentService.removeStudent(student.getId(), filePath)).thenReturn(student.getId());
		ResponseEntity<Object> response = studentController.removeStudent(102);
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
	}
	
	@Test
	public void testupdateStudent() throws Exception {
		Student student = getTestStudent();
		when(studentService.updateStudent(student)).thenReturn(student);
		ResponseEntity<Object> response =  studentController.updateStudent(student);
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

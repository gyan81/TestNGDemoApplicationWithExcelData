package com.cts.controller;

import java.util.List;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.cts.service.impl.StudentServiceImpl;
import com.cts.exception.CustomExceptionHandler;
import com.cts.model.Student;

@RestController
public class StudentController {
	@Autowired
	StudentServiceImpl studentService;
	
	private String filePath = "./src/main/resources/excel/students.xlsx";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/create-student", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> createStudent(@RequestBody final Student student){
		System.out.println("Under createStudent .............");
		Student newStudent = studentService.createStudent(student);
		if (newStudent == null) {			
			return new ResponseEntity("Unable to create student", HttpStatus.INTERNAL_SERVER_ERROR);
		}else {			
			return new ResponseEntity("Student created successfully.", HttpStatus.OK);
		}
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/update-student", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student) {
		System.out.println("Inside updatestudent api ....");
		Student updatedStudent = studentService.updateStudent(student);
		if (updatedStudent == null) {			
			return new ResponseEntity("Unable to update student", HttpStatus.INTERNAL_SERVER_ERROR);
		}else {			
			return new ResponseEntity("Student updated successfully.", HttpStatus.OK);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/get-student-by-id", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> searchStudentById(@RequestParam int id) {
		Student student = studentService.searchStudentById(id);
		
		if (student ==null) {
			return new ResponseEntity("Student not found in the system", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity(student, HttpStatus.OK);
		}
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Object> removeStudent(@PathVariable("id") final int id) {
		System.out.println("Inside removeStudent method controller .... ");
		int studentId = studentService.removeStudent(id, filePath);
		System.out.println("studentId :" +studentId);
		if (studentId ==0) {
			return new ResponseEntity("Student not found in the system", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity("Student with Id : " + studentId + " removed successfully", HttpStatus.OK);
		}
	}
}

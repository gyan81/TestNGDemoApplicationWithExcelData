package com.cts.repository;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.cts.controller.AbstractTest;
import com.cts.model.Student;
import com.cts.repository.impl.StudentRepositoryImpl;
import com.cts.util.RWExcelStudent;

public class StudentRepositoryTest extends AbstractTest {

	@InjectMocks
	StudentRepositoryImpl studentRepository;
	@Mock
	RWExcelStudent rWExcelStudent;

	private String filePath = "./src/main/resources/excel/students.xlsx";

	@Test
	public void testCreateStudent() {
		Student student = new Student();
		student.setId(107);
		student.setName("Bhaskar2");
		student.setGrade("A");
		student.setAge(38);
		when(rWExcelStudent.writeExcel(student, "./src/main/resources/excel/students.xlsx")).thenReturn(student);
		studentRepository.createStudent(student);
	}

	@Test
	public void testUpdateStudent() {
		Student student = new Student();
		student.setId(107);
		when(rWExcelStudent.updateExcel(student, "./src/main/resources/excel/students.xlsx")).thenReturn(student);
		Student res = studentRepository.updateStudent(student);
		assertNotEquals(res, null);
	}

	@Test
	public void testRemoveStudent() {
		Student student = new Student();
		student.setId(107);
		when(rWExcelStudent.removeStudentFromExcel("./src/main/resources/excel/students.xlsx", student.getId()))
				.thenReturn(107);
		int res = studentRepository.removeStudent(student.getId(), filePath);
		assertEquals(res, 107);
	}

	@Test
	public void testSearchStudentById() {
		Student student = new Student();
		student.setId(102);
		when(rWExcelStudent.getStudentById(student.getId(), "./src/main/resources/excel/students.xlsx"))
				.thenReturn(student);
		Student res = studentRepository.searchStudentById(student.getId());
		assertNotEquals(res, null);
	}

}

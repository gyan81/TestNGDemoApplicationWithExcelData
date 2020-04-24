package com.cts.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.nio.file.NoSuchFileException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.model.Student;

@RunWith(SpringRunner.class)
public class RWExcelStudentTest {
	
	
	RWExcelStudent rWExcelStudent;
	private String filePath = "./src/main/resources/excel/students.xlsx";
	private String filePathNoFile = "./src/main/resources/excel/nofile.xlsx";
	
	@Test
	public void readExcelTest() {
		rWExcelStudent = new RWExcelStudent();
		List<Student> students = rWExcelStudent.readExcel(filePath);
		assertNotNull(students);
	}
	
	@Test
	public void readExcelTestException() {
		rWExcelStudent = new RWExcelStudent();
		List<Student> students = rWExcelStudent.readExcel(filePathNoFile);
		assertEquals(null, students);

	}
	
	@Test
	public void writeExcelTest() {
		rWExcelStudent = new RWExcelStudent();
		Student student = getDummyStudent();
		int writeExcelResponse = rWExcelStudent.writeExcel(student, filePath).getId();
		assertEquals("102", writeExcelResponse);
	}
	
	@Test ( expected = NoSuchFileException.class)
	public void writeExcelException() throws NoSuchFileException {
		rWExcelStudent = new RWExcelStudent();
		rWExcelStudent.writeExcel(null, filePathNoFile);
		throw new NoSuchFileException(filePathNoFile);
	}
	
	
	@Test
	public void getStudentByIdTest() {
		rWExcelStudent = new RWExcelStudent();
		Student student = getDummyStudent();
		Student studentById = rWExcelStudent.getStudentById(student.getId(), filePath);
		assertNotEquals(student.getId(), studentById);
	}
	
	@Test
	public void writeStudentExcelTest() {
		rWExcelStudent = new RWExcelStudent();
		Student student = getDummyStudent();
		String response = rWExcelStudent.writeStudentExcel(student, filePath);
		assertNotEquals("SStudent has been creadted Successfully.", response);
	}
	
	
	private Student getDummyStudent() {
		Student student = new Student();
		student.setId(102);
		student.setName("Bhaskar");
		student.setGrade("B");
		student.setAge(35);
		return student;
	}

}

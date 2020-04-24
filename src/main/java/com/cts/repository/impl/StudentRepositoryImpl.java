package com.cts.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.model.Student;
import com.cts.repository.iface.IStudentRepository;
import com.cts.util.RWExcelStudent;


@Repository("studentRepository")
public class StudentRepositoryImpl implements IStudentRepository {
	
	@Autowired
	private RWExcelStudent rWExcelStudent;
	
	@Override
	public Student createStudent(Student student) {
		return rWExcelStudent.writeExcel(student, "./src/main/resources/excel/students.xlsx");
	}

	@Override
	public Student updateStudent(Student student) {
		return rWExcelStudent.updateExcel(student, "./src/main/resources/excel/students.xlsx");
	}

	@Override
	public List<Student> searchStudentByAge(int age) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> searchStudentByGrade(String grade) {
		return rWExcelStudent.getStudentByGrade(grade, "./src/main/resources/excel/students.xlsx");
	}

	@Override
	public Student searchStudentById(int id) {
		return rWExcelStudent.getStudentById(id, "./src/main/resources/excel/students.xlsx");
	}

	@Override
	public List<Student> searchStudentByAgeAndGrade(int age, String grade) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int removeStudent(int id, String filePath) {
		return rWExcelStudent.removeStudentFromExcel(filePath, id);
	}

}

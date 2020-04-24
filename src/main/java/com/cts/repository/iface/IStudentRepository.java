package com.cts.repository.iface;

import java.util.List;

import com.cts.model.Student;

public interface IStudentRepository {
	
	
	public Student createStudent(Student student);

	public Student updateStudent(Student student);

	public List<Student> searchStudentByAge(int age);

	public List<Student> searchStudentByGrade(String grade);

	public Student searchStudentById(int id);

	public List<Student> searchStudentByAgeAndGrade(int age, String grade);

	public int removeStudent(int id, String filePath);

}

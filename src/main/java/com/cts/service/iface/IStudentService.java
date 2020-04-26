package com.cts.service.iface;

import java.util.List;

import com.cts.model.Student;

public interface IStudentService {
	
	public Student createStudent(Student student);

	public Student updateStudent(Student student);
	
	public Student searchStudentById(int id);

	public int removeStudent(int id, String filePath);



}

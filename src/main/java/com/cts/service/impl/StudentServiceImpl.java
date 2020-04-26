package com.cts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Student;
import com.cts.repository.iface.IStudentRepository;
import com.cts.service.iface.IStudentService;

@Service("studentService")
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private IStudentRepository studentRepository;

	private static List<Student> students;

	@Override
	public Student createStudent(Student student) {
		return studentRepository.createStudent(student);
	}

	@Override
	public Student updateStudent(Student student) {
		Student resObj = studentRepository.updateStudent(student);
		return resObj;
	}

	@Override
	public Student searchStudentById(int id) {
		return studentRepository.searchStudentById(id);
	}

	@Override
	public int removeStudent(int id, String filePath) {
		return studentRepository.removeStudent(id, filePath);
	}

}

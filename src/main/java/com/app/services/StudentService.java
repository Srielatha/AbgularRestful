package com.app.services;

import java.util.List;

import com.app.model.Student;

public interface StudentService {

	public Student createStudent(Student student) throws Exception;

	public Student updateStudent(Student student) throws Exception;

	public List<Student> getAllStudents() throws Exception;

	public Student getStudentById(Long studentId) throws Exception;

	public void deleteStudent(Long studentId) throws Exception;

}

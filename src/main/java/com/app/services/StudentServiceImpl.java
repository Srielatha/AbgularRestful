package com.app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Student;
import com.app.repositories.StudentRepository;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student createStudent(Student student) throws Exception {
		student.setCreatedDateTime(LocalDateTime.now());
		student.setUpdatedDateTime(LocalDateTime.now());
		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(Student student) throws Exception {
		student.setUpdatedDateTime(LocalDateTime.now());
		System.out.println(student);
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() throws Exception {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(Long studentId) throws Exception {
		return studentRepository.findOne(studentId);
	}

	@Override
	public void deleteStudent(Long studentId) throws Exception {
		Student student = studentRepository.findOne(studentId);
		if (student == null) {
			throw new Exception("sorry the given studentId-" + studentId + "is not found");
		}
		studentRepository.delete(studentId);
	}

}
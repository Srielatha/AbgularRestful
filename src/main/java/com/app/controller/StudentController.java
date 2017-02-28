package com.app.controller;

import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Student;
import com.app.services.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping
	public List<Student> getAllStudents() throws Exception {
		return studentService.getAllStudents();
	}

	@GetMapping("/{studentId}")
	public ResponseEntity<Student> getStudent(@PathVariable("studentId") Long studentId) throws Exception {
		Student student = studentService.getStudentById(studentId);
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(2, TimeUnit.MINUTES).cachePrivate())
				.lastModified(student.getUpdatedDateTime().toInstant(ZoneOffset.UTC).toEpochMilli()).body(student);
	}

	@PostMapping
	public Student create(@Valid @RequestBody Student student) throws Exception {
		return studentService.createStudent(student);
	}

	@PutMapping("/{studentId}")
	public Student update(@RequestBody Student student) throws Exception {
		return studentService.updateStudent(student);
	}

	@DeleteMapping(path = "/{studentId}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long studentId) throws Exception {
		studentService.deleteStudent(studentId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
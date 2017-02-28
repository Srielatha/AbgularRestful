package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	public Optional<Student> findByStudentId(Long studentId);

}

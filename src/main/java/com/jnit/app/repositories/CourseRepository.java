package com.jnit.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jnit.app.model.Course;
import com.jnit.app.model.Topic;

public interface CourseRepository extends JpaRepository<Course, Long> {

	public Optional<Course> findByName(String courseName);

	public List<Course> findByAuthor(String author);

	public Long countByName(String courseName);

	public Optional<Course> findByNameAndAuthor(String courseName, String author);

	public List<Course> findByNameOrAuthor(String courseName, String author);

	public List<Course> findDistinctByType(String courseType);

	public List<Course> findFirst3ByNameOrderByNameAsc(String courseName);

	public Topic save(Topic topic);

}

package com.jnit.app.services;

import java.util.List;
import com.jnit.app.model.Course;
import com.jnit.app.model.Topic;

public interface CourseService {

	public Course createCourse(Course course) throws Exception;

	public Course updateCourse(Course course) throws Exception;

	public List<Course> getAllCourses() throws Exception;

	public Course getCourseById(Long courseId) throws Exception;

	public void deleteCourse(Long courseId) throws Exception;

	Topic updateTopic(Topic topic) throws Exception;

	void deleteTopic(Long topicId) throws Exception;

	public Topic createCourse(Topic topic) throws Exception;

}

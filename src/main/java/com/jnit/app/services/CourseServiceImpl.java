package com.jnit.app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jnit.app.model.Course;
import com.jnit.app.model.Topic;
import com.jnit.app.repositories.CourseRepository;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course createCourse(Course course) throws Exception {
		course.setCreatedDateTime(LocalDateTime.now());
		course.setUpdatedDateTime(LocalDateTime.now());
		return courseRepository.save(course);
	}

	public Course updateCourse(Course course) throws Exception {
		course.setUpdatedDateTime(LocalDateTime.now());
		return courseRepository.save(course);
	}

	public List<Course> getAllCourses() throws Exception {
		return courseRepository.findAll();
	}

	public Course getCourseById(Long courseId) throws Exception {
		return courseRepository.findOne(courseId);
	}

	public void deleteCourse(Long courseId) throws Exception {
		Course course = courseRepository.findOne(courseId);
		if (course == null) {
			throw new Exception("sorry the given courseId-" + courseId + "is not found");
		}
		courseRepository.delete(courseId);
	}

	public Topic updateTopic(Topic topic) throws Exception {
		topic.setUpdatedDateTime(LocalDateTime.now());
		return courseRepository.save(topic);
	}

	public void deleteTopic(Long topicId) throws Exception {
		Course course = courseRepository.findOne(topicId);
		if (course == null) {
			throw new Exception("sorry the given topicId-" + topicId + "is not found");
		}
		courseRepository.delete(topicId);

	}

}

package com.jnit.app.controller;

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

import com.jnit.app.model.Course;
import com.jnit.app.model.Topic;
import com.jnit.app.services.CourseService;

@RestController
@RequestMapping("topics")
public class TopicController {
	@Autowired
	private CourseService courseService;

	@PutMapping
	public Topic update(@RequestBody Topic topic) throws Exception {
		return courseService.updateTopic(topic);
	}

	@DeleteMapping(path = "/{topicId}")
	public ResponseEntity<HttpStatus> deleteTopic(@PathVariable Long topicId) throws Exception {
		courseService.deleteTopic(topicId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}

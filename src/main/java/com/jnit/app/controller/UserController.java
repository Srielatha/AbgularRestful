package com.jnit.app.controller;

import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;
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

import com.jnit.app.model.User;
import com.jnit.app.services.UserService;

@RestController
@RequestMapping("users")

public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> getAllUsers() throws Exception {
		return userService.getAllUsers();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) throws Exception {
		User user = userService.getUserById(userId);
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(2, TimeUnit.MINUTES).cachePrivate())
				.lastModified(user.getUpdatedDateTime().toInstant(ZoneOffset.UTC).toEpochMilli()).body(user);
	}

	@PostMapping
	public User create(@Valid @RequestBody User user) throws Exception {
		return userService.createUser(user);
	}

	@PutMapping
	public User update(@RequestBody User user) throws Exception {
		return userService.updateUser(user);
	}

	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") Long userId) throws Exception {
		userService.deleteUser(userId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}

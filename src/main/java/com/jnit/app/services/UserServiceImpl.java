package com.jnit.app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jnit.app.model.User;
import com.jnit.app.repositories.UserRepository;

@Service
@Transactional // (propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) throws Exception {
		user.setCreatedDateTime(LocalDateTime.now());
		user.setUpdatedDateTime(LocalDateTime.now());
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) throws Exception {
		user.setUpdatedDateTime(LocalDateTime.now());
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long userId) throws Exception {
		return userRepository.findOne(userId);
	}

	@Override
	public void deleteUser(Long userId) throws Exception {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new Exception("User with " + userId + " not found");
		}
		userRepository.delete(userId);
	}

}

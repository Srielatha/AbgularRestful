package com.jnit.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jnit.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUserName(String userName);

	public List<User> findByLName(String lastName);

	public Long countByLName(String lastName);

	public Optional<User> findByFNameAndLName(String firstName, String lastName);

	public List<User> findByFNameOrLName(String firstName, String lastName);

	public List<User> findDistinctByMName(String middleName);

	public List<User> findFirst3ByLNameOrderByUserNameAsc(String lastName);

}

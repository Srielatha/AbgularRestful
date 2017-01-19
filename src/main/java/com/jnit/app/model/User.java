package com.jnit.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = { "courses" })
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "userId" })
@Builder
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Email(message = "user name is invalid")
	@NotBlank(message = "user name can not be blank")
	private String userName;

	@NotBlank(message = "first name can not be blank")
	private String fName;

	@NotBlank(message = "last name can not be blank")
	private String lName;

	private String mName;

	@NotBlank(message = "password can not be blank")
	private String password;

	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate dob;

	private String phoneNumber;

	private LocalDateTime createdDateTime;

	private LocalDateTime updatedDateTime;

	@Version
	private Integer versionId;

	@ManyToMany(mappedBy = "registeredUsers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Course> courses = new ArrayList<>();

}

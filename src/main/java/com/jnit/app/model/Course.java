package com.jnit.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@JsonInclude(Include.NON_EMPTY)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "courseId" })
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;

	@NotBlank(message = "name can not be blank")
	private String name;

	@NotBlank(message = "author can not be blank")
	private String author;
	@Enumerated(EnumType.ORDINAL)
	private SkillLevel level;
	private String description;
	private String prereqs;
	private BigDecimal fee;
	private String endGoal;

	@NotBlank(message = "type can not be blank")
	private String type;
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "course_user", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "courseId"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
	private List<User> registeredUsers = new ArrayList<>();
	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderColumn
	@Fetch(FetchMode.SELECT)
	private List<Topic> topics = new ArrayList<>();

	private LocalDateTime createdDateTime;

	private LocalDateTime updatedDateTime;

	@Version
	private Integer versionId;

	@Transient
	private String authorFullName;

}
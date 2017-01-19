package com.jnit.app.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "topicId" })
@ToString(exclude = { "course" })
public class Topic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long topicId;

	private String name;
	private String duration;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	private LocalDateTime createdDateTime;

	private LocalDateTime updatedDateTime;

	@Version
	private Integer versionId;

}

package com.developer.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobId;

	@NotBlank(message = "ok.................")
	private String title;

	private String description;

	private String maxSalary;

	private String minSalary;

	private String location;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id")
	private Company company;
}

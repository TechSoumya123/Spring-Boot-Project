package com.developer.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyId;

	@NotBlank(message = "Company name must be not empty")
	@Column(name = "company_name", length = 100, nullable = false)
	private String companyName;

	@NotBlank(message = "Company description also required")
	@Column(name = "description", length = 200, unique = true, nullable = false)
	private String description;

	@JsonIgnore
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Job> listOfJobs = new ArrayList<>();

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Review> listOfReviews = new ArrayList<>();
}

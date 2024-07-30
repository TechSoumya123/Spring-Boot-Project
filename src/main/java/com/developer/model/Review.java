package com.developer.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;

	private String title;

	private String description;

	private double rating;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

}

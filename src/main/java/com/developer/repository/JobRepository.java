package com.developer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developer.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}

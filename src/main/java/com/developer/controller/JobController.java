package com.developer.controller;

import java.util.List;
import java.util.Objects;

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

import com.developer.model.Job;
import com.developer.service.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = { "/job" })
public class JobController {

	private final JobService jobService;

	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@GetMapping(path = { "/get-All-jobs" })
	public ResponseEntity<List<Job>> getAllJobs() {
		try {
			var listOfJobs = jobService.getAllJobs();
			return listOfJobs.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
					: ResponseEntity.status(HttpStatus.OK).body(listOfJobs);
		} catch (Exception exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping(path = { "/create-job" })
	public ResponseEntity<String> createJob(@RequestBody @Valid Job job) {
		try {
			jobService.createNewJob(job);
			return ResponseEntity.status(HttpStatus.CREATED).body("Job created successfully");
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@GetMapping(path = { "/get-Particular-Job/{id}" })
	public ResponseEntity<Object> getParticularJob(@PathVariable("id") Long jobId) {
		try {
			var job = jobService.getJobFindById(jobId);
			return Objects.isNull(job)
					? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found with this id " + jobId)
					: ResponseEntity.status(HttpStatus.OK).body(job);
		} catch (Exception exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping(path = { "/update-Particular-Job/{id}" })
	public ResponseEntity<String> updateParticularJob(@PathVariable("id") Long jobId, @RequestBody @Valid Job job) {
		try {
			return jobService.updateJob(jobId, job)
					? ResponseEntity.status(HttpStatus.OK).body("Job updated successfully")
					: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jon not found with this id " + jobId);
		} catch (Exception exception) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping(path = { "/delete-job-byId/{id}" })
	public ResponseEntity<String> jobDeletedById(@PathVariable("id") Long jobId) {
		try {
			return jobService.deleteJobById(jobId)
					? ResponseEntity.status(HttpStatus.OK).body("Job deleted successfully ")
					: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jon not found with this id " + jobId);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}
}

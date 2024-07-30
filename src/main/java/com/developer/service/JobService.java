package com.developer.service;

import java.util.List;

import com.developer.model.Job;

public interface JobService {

	List<Job> getAllJobs();

	void createNewJob(Job job);

	Job getJobFindById(Long jobId);

	boolean deleteJobById(Long jobId);

	boolean updateJob(Long jobId, Job job);
}

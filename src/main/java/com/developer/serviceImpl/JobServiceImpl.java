package com.developer.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.developer.model.Job;
import com.developer.repository.JobRepository;
import com.developer.service.JobService;

@Service
public class JobServiceImpl implements JobService {

	private final JobRepository jobRepository;

	public JobServiceImpl(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public List<Job> getAllJobs() {
		return jobRepository.findAll();
	}

	@Override
	public void createNewJob(Job job) {
		jobRepository.save(job);

	}

	@Override
	public Job getJobFindById(Long jobId) {
		return jobRepository.findById(jobId).orElse(null);

	}

	@Override
	public boolean deleteJobById(Long jobId) {
		if (Objects.nonNull(getJobFindById(jobId))) {
			jobRepository.deleteById(jobId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateJob(Long jobId, Job job) {
		Job jobFindById = getJobFindById(jobId);
		if (Objects.nonNull(jobFindById)) {
			jobFindById.setTitle(job.getTitle());
			jobFindById.setDescription(job.getDescription());
			jobFindById.setMaxSalary(job.getMaxSalary());
			jobFindById.setMinSalary(job.getMinSalary());
			jobFindById.setLocation(job.getLocation());
			jobRepository.save(jobFindById);
			return true;
		} else {
			return false;
		}
	}

}

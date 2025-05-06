package com.example.jobfinder.service;

import com.example.jobfinder.entity.JobListing;
import com.example.jobfinder.repository.JobListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobListingRepository jobListingRepository;

    public JobListing createJob(JobListing job) {
        return jobListingRepository.save(job);
    }

    public JobListing updateJob(Long id, JobListing updatedJob, String email) {
        JobListing existingJob = jobListingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        if (!existingJob.getEmployer().getEmail().equals(email)) {
            throw new RuntimeException("Not authorized to update this job");
        }
        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setSalaryMin(updatedJob.getSalaryMin());
        existingJob.setSalaryMax(updatedJob.getSalaryMax());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setCompany(updatedJob.getCompany());
        return jobListingRepository.save(existingJob);
    }

    public void deleteJob(Long id, String email) {
        JobListing job = jobListingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        if (!job.getEmployer().getEmail().equals(email)) {
            throw new RuntimeException("Not authorized to delete this job");
        }
        jobListingRepository.deleteById(id);
    }

    public List<JobListing> getAllJobs() {
        return jobListingRepository.findAll();
    }

    public List<JobListing> searchJobs(String title) {
        return jobListingRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<JobListing> getJobsByEmployer(Long employerId) {
        return jobListingRepository.findByEmployerId(employerId);
    }
}
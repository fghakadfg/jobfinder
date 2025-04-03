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

    public List<JobListing> searchJobs(String title) {
        return jobListingRepository.findByTitleContainingIgnoreCase(title);
    }
}
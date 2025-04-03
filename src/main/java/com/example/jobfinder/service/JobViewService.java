package com.example.jobfinder.service;

import com.example.jobfinder.entity.JobView;
import com.example.jobfinder.repository.JobViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobViewService {
    @Autowired
    private JobViewRepository jobViewRepository;

    public JobView addJobView(JobView jobView) {
        return jobViewRepository.save(jobView);
    }

    public List<JobView> getJobViewsByUserId(Long userId) {
        return jobViewRepository.findByUserId(userId);
    }
}
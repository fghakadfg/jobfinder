package com.example.jobfinder.service;

import com.example.jobfinder.entity.JobRequirement;
import com.example.jobfinder.repository.JobRequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobRequirementService {
    @Autowired
    private JobRequirementRepository jobRequirementRepository;

    public JobRequirement addRequirement(JobRequirement requirement) {
        return jobRequirementRepository.save(requirement);
    }

    public List<JobRequirement> getRequirementsByJobId(Long jobId) {
        return jobRequirementRepository.findByJobId(jobId);
    }
}
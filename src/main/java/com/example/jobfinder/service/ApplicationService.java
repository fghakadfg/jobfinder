package com.example.jobfinder.service;

import com.example.jobfinder.entity.Application;
import com.example.jobfinder.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public Application submitApplication(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByApplicant(Long applicantId) {
        return applicationRepository.findByApplicantId(applicantId);
    }

    public List<Application> getApplicationsByJobId(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }
}
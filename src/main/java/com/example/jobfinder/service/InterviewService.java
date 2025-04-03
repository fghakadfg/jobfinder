package com.example.jobfinder.service;

import com.example.jobfinder.entity.Interview;
import com.example.jobfinder.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;

    public Interview scheduleInterview(Interview interview) {
        return interviewRepository.save(interview);
    }

    public List<Interview> getInterviewsByApplicationId(Long applicationId) {
        return interviewRepository.findByApplicationId(applicationId);
    }
}
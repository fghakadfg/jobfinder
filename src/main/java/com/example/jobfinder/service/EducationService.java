package com.example.jobfinder.service;

import com.example.jobfinder.entity.Education;
import com.example.jobfinder.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    @Autowired
    private EducationRepository educationRepository;

    public Education addEducation(Education education) {
        return educationRepository.save(education);
    }

    public List<Education> getEducationByUserId(Long userId) {
        return educationRepository.findByUserId(userId);
    }
}
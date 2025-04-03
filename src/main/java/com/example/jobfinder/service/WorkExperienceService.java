package com.example.jobfinder.service;

import com.example.jobfinder.entity.WorkExperience;
import com.example.jobfinder.repository.WorkExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkExperienceService {
    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    public WorkExperience addWorkExperience(WorkExperience workExperience) {
        return workExperienceRepository.save(workExperience);
    }

    public List<WorkExperience> getWorkExperienceByUserId(Long userId) {
        return workExperienceRepository.findByUserId(userId);
    }
}
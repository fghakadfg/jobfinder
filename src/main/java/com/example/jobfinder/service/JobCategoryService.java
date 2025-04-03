package com.example.jobfinder.service;

import com.example.jobfinder.entity.JobCategory;
import com.example.jobfinder.repository.JobCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobCategoryService {
    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    public JobCategory createCategory(JobCategory category) {
        return jobCategoryRepository.save(category);
    }

    public List<JobCategory> getAllCategories() {
        return jobCategoryRepository.findAll();
    }
}
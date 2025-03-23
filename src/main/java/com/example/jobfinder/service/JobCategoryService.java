package com.example.jobfinder.service;

import com.example.jobfinder.model.JobCategory;
import com.example.jobfinder.repository.JobCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobCategoryService {

    private final JobCategoryRepository jobCategoryRepository;

    @Autowired
    public JobCategoryService(JobCategoryRepository jobCategoryRepository) {
        this.jobCategoryRepository = jobCategoryRepository;
    }

    public List<JobCategory> getAllJobCategories() {
        return jobCategoryRepository.findAll();
    }

    // Дополнительные методы можно добавить по необходимости
}

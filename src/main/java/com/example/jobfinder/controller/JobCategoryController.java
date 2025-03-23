package com.example.jobfinder.controller;

import com.example.jobfinder.model.JobCategory;
import com.example.jobfinder.service.JobCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobCategoryController {

    private final JobCategoryService jobCategoryService;

    @Autowired
    public JobCategoryController(JobCategoryService jobCategoryService) {
        this.jobCategoryService = jobCategoryService;
    }

    @GetMapping("/job-categories")
    public List<JobCategory> getAllJobCategories() {
        return jobCategoryService.getAllJobCategories();
    }
}

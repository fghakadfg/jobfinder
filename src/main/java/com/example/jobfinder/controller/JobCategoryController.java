package com.example.jobfinder.controller;

import com.example.jobfinder.entity.JobCategory;
import com.example.jobfinder.service.JobCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class JobCategoryController {
    @Autowired
    private JobCategoryService jobCategoryService;

    @PostMapping("/categories")
    public ResponseEntity<JobCategory> createCategory(@RequestBody JobCategory category) {
        return ResponseEntity.ok(jobCategoryService.createCategory(category));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<JobCategory>> getAllCategories() {
        return ResponseEntity.ok(jobCategoryService.getAllCategories());
    }
}
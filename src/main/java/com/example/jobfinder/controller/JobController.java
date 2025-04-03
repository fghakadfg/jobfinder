package com.example.jobfinder.controller;

import com.example.jobfinder.entity.JobListing;
import com.example.jobfinder.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/employer/jobs")
    public ResponseEntity<JobListing> createJob(@RequestBody JobListing job) {
        return ResponseEntity.ok(jobService.createJob(job));
    }

    @GetMapping("/jobs/search")
    public ResponseEntity<List<JobListing>> searchJobs(@RequestParam String title) {
        return ResponseEntity.ok(jobService.searchJobs(title));
    }
}
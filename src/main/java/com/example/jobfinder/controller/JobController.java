package com.example.jobfinder.controller;

import com.example.jobfinder.dto.JobListingDTO;
import com.example.jobfinder.entity.Company;
import com.example.jobfinder.entity.JobListing;
import com.example.jobfinder.entity.User;
import com.example.jobfinder.service.CompanyService;
import com.example.jobfinder.service.JobService;
import com.example.jobfinder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobController {
    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/employer/jobs")
    public ResponseEntity<JobListing> createJob(@Valid @RequestBody JobListingDTO jobDTO, Authentication authentication) {
        User employer = userService.findByEmail(authentication.getName());
        JobListing job = new JobListing();
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setSalaryMin(jobDTO.getSalaryMin());
        job.setSalaryMax(jobDTO.getSalaryMax());
        job.setLocation(jobDTO.getLocation());
        job.setCompany(jobDTO.getCompanyId() != null ? companyService.getCompanyById(jobDTO.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found")) : null);
        job.setEmployer(employer);
        return ResponseEntity.ok(jobService.createJob(job));
    }

    @PutMapping("/employer/jobs/{id}")
    public ResponseEntity<JobListing> updateJob(@PathVariable Long id, @Valid @RequestBody JobListingDTO jobDTO, Authentication authentication) {
        User employer = userService.findByEmail(authentication.getName());
        JobListing updatedJob = new JobListing();
        updatedJob.setTitle(jobDTO.getTitle());
        updatedJob.setDescription(jobDTO.getDescription());
        updatedJob.setSalaryMin(jobDTO.getSalaryMin());
        updatedJob.setSalaryMax(jobDTO.getSalaryMax());
        updatedJob.setLocation(jobDTO.getLocation());
        updatedJob.setCompany(jobDTO.getCompanyId() != null ? companyService.getCompanyById(jobDTO.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found")) : null);
        return ResponseEntity.ok(jobService.updateJob(id, updatedJob, employer.getEmail()));
    }

    @DeleteMapping("/employer/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id, Authentication authentication) {
        User employer = userService.findByEmail(authentication.getName());
        jobService.deleteJob(id, employer.getEmail());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobListing>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/jobs/search")
    public ResponseEntity<List<JobListing>> searchJobs(@RequestParam String title) {
        return ResponseEntity.ok(jobService.searchJobs(title));
    }

    @GetMapping("/employer/jobs")
    public ResponseEntity<List<JobListing>> getJobsByEmployer(Authentication authentication) {
        User employer = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(jobService.getJobsByEmployer(employer.getId()));
    }
}
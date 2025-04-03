package com.example.jobfinder.controller;

import com.example.jobfinder.entity.Application;
import com.example.jobfinder.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicant")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/applications")
    public ResponseEntity<Application> submitApplication(@RequestBody Application application) {
        return ResponseEntity.ok(applicationService.submitApplication(application));
    }

    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getApplications(@RequestParam Long applicantId) {
        return ResponseEntity.ok(applicationService.getApplicationsByApplicant(applicantId));
    }
}
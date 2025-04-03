package com.example.jobfinder.controller;

import com.example.jobfinder.entity.JobRequirement;
import com.example.jobfinder.service.JobRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer")
public class JobRequirementController {
    @Autowired
    private JobRequirementService jobRequirementService;

    @PostMapping("/requirements")
    public ResponseEntity<JobRequirement> addRequirement(@RequestBody JobRequirement requirement) {
        return ResponseEntity.ok(jobRequirementService.addRequirement(requirement));
    }

    @GetMapping("/requirements")
    public ResponseEntity<List<JobRequirement>> getRequirementsByJob(@RequestParam Long jobId) {
        return ResponseEntity.ok(jobRequirementService.getRequirementsByJobId(jobId));
    }
}
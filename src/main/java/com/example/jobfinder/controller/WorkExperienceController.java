package com.example.jobfinder.controller;

import com.example.jobfinder.entity.WorkExperience;
import com.example.jobfinder.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicant")
public class WorkExperienceController {
    @Autowired
    private WorkExperienceService workExperienceService;

    @PostMapping("/work-experience")
    public ResponseEntity<WorkExperience> addWorkExperience(@RequestBody WorkExperience workExperience) {
        return ResponseEntity.ok(workExperienceService.addWorkExperience(workExperience));
    }

    @GetMapping("/work-experience")
    public ResponseEntity<List<WorkExperience>> getWorkExperienceByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(workExperienceService.getWorkExperienceByUserId(userId));
    }
}
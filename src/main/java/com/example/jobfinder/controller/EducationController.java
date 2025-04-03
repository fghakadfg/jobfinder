package com.example.jobfinder.controller;

import com.example.jobfinder.entity.Education;
import com.example.jobfinder.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicant")
public class EducationController {
    @Autowired
    private EducationService educationService;

    @PostMapping("/education")
    public ResponseEntity<Education> addEducation(@RequestBody Education education) {
        return ResponseEntity.ok(educationService.addEducation(education));
    }

    @GetMapping("/education")
    public ResponseEntity<List<Education>> getEducationByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(educationService.getEducationByUserId(userId));
    }
}
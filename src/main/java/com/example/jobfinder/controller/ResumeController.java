package com.example.jobfinder.controller;

import com.example.jobfinder.entity.Resume;
import com.example.jobfinder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicant")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @PostMapping("/resumes")
    public ResponseEntity<Resume> createResume(@RequestBody Resume resume) {
        return ResponseEntity.ok(resumeService.createResume(resume));
    }

    @GetMapping("/resumes")
    public ResponseEntity<List<Resume>> getResumesByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(resumeService.getResumesByUserId(userId));
    }
}
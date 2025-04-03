package com.example.jobfinder.controller;

import com.example.jobfinder.entity.Interview;
import com.example.jobfinder.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer")
public class InterviewController {
    @Autowired
    private InterviewService interviewService;

    @PostMapping("/interviews")
    public ResponseEntity<Interview> scheduleInterview(@RequestBody Interview interview) {
        return ResponseEntity.ok(interviewService.scheduleInterview(interview));
    }

    @GetMapping("/interviews")
    public ResponseEntity<List<Interview>> getInterviewsByApplication(@RequestParam Long applicationId) {
        return ResponseEntity.ok(interviewService.getInterviewsByApplicationId(applicationId));
    }
}
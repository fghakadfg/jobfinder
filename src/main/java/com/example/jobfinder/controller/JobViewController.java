package com.example.jobfinder.controller;

import com.example.jobfinder.entity.JobView;
import com.example.jobfinder.service.JobViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobViewController {
    @Autowired
    private JobViewService jobViewService;

    @PostMapping("/job-views")
    public ResponseEntity<JobView> addJobView(@RequestBody JobView jobView) {
        return ResponseEntity.ok(jobViewService.addJobView(jobView));
    }

    @GetMapping("/job-views")
    public ResponseEntity<List<JobView>> getJobViewsByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(jobViewService.getJobViewsByUserId(userId));
    }
}
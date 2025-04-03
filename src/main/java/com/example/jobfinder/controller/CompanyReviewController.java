package com.example.jobfinder.controller;

import com.example.jobfinder.entity.CompanyReview;
import com.example.jobfinder.service.CompanyReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicant")
public class CompanyReviewController {
    @Autowired
    private CompanyReviewService companyReviewService;

    @PostMapping("/reviews")
    public ResponseEntity<CompanyReview> createReview(@RequestBody CompanyReview review) {
        return ResponseEntity.ok(companyReviewService.createReview(review));
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<CompanyReview>> getReviewsByCompany(@RequestParam Long companyId) {
        return ResponseEntity.ok(companyReviewService.getReviewsByCompanyId(companyId));
    }
}
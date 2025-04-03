package com.example.jobfinder.service;

import com.example.jobfinder.entity.CompanyReview;
import com.example.jobfinder.repository.CompanyReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyReviewService {
    @Autowired
    private CompanyReviewRepository companyReviewRepository;

    public CompanyReview createReview(CompanyReview review) {
        return companyReviewRepository.save(review);
    }

    public List<CompanyReview> getReviewsByCompanyId(Long companyId) {
        return companyReviewRepository.findByCompanyId(companyId);
    }
}
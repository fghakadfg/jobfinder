package com.example.jobfinder.repository;

import com.example.jobfinder.entity.CompanyReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyReviewRepository extends JpaRepository<CompanyReview, Long> {
    List<CompanyReview> findByCompanyId(Long companyId);
}
package com.example.jobfinder.repository;

import com.example.jobfinder.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findByApplicantId(Long applicantId);
    List<Response> findByJobId(Long jobId);
    boolean existsByApplicantIdAndJobId(Long applicantId, Long jobId);
}
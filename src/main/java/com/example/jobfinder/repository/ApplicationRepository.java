package com.example.jobfinder.repository;

import com.example.jobfinder.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByApplicantId(Long applicantId);
    List<Application> findByJobId(Long jobId);
}
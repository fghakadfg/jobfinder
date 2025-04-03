package com.example.jobfinder.repository;

import com.example.jobfinder.entity.JobRequirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRequirementRepository extends JpaRepository<JobRequirement, Long> {
    List<JobRequirement> findByJobId(Long jobId);
}
package com.example.jobfinder.repository;

import com.example.jobfinder.entity.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobListingRepository extends JpaRepository<JobListing, Long> {
    List<JobListing> findByTitleContainingIgnoreCase(String title);
    List<JobListing> findByLocationContainingIgnoreCase(String location);
    List<JobListing> findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(String title, String location);
    List<JobListing> findByEmployerId(Long employerId);
}
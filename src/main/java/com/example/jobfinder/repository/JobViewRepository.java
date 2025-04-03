package com.example.jobfinder.repository;

import com.example.jobfinder.entity.JobView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobViewRepository extends JpaRepository<JobView, Long> {
    List<JobView> findByUserId(Long userId);
}
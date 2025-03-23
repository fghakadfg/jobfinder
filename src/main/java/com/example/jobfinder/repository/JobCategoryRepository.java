package com.example.jobfinder.repository;

import com.example.jobfinder.model.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCategoryRepository extends JpaRepository<JobCategory, Long> {
    // Дополнительные методы можно добавить по необходимости, например:
    // List<JobCategory> findByName(String name);
}

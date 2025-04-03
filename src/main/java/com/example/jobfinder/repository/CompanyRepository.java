package com.example.jobfinder.repository;

import com.example.jobfinder.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
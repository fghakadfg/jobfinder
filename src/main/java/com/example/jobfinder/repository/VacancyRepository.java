package com.example.jobfinder.repository;

import com.example.jobfinder.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByTitleContainingIgnoreCase(String title);
    List<Vacancy> findByLocationContainingIgnoreCase(String location);
    List<Vacancy> findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(String title, String location);
    List<Vacancy> findByEmployerId(Long employerId);

    List<Vacancy> findBySalaryMinGreaterThanEqual(Integer salaryMin);
    List<Vacancy> findBySalaryMaxLessThanEqual(Integer salaryMax);
    List<Vacancy> findBySalaryMinGreaterThanEqualAndSalaryMaxLessThanEqual(Integer salaryMin, Integer salaryMax);

    List<Vacancy> findByTitleContainingIgnoreCaseAndSalaryMinGreaterThanEqual(String title, Integer salaryMin);
    List<Vacancy> findByTitleContainingIgnoreCaseAndSalaryMaxLessThanEqual(String title, Integer salaryMax);
    List<Vacancy> findByTitleContainingIgnoreCaseAndSalaryMinGreaterThanEqualAndSalaryMaxLessThanEqual(
            String title, Integer salaryMin, Integer salaryMax);

    List<Vacancy> findByLocationContainingIgnoreCaseAndSalaryMinGreaterThanEqual(String location, Integer salaryMin);
    List<Vacancy> findByLocationContainingIgnoreCaseAndSalaryMaxLessThanEqual(String location, Integer salaryMax);
    List<Vacancy> findByLocationContainingIgnoreCaseAndSalaryMinGreaterThanEqualAndSalaryMaxLessThanEqual(
            String location, Integer salaryMin, Integer salaryMax);

    List<Vacancy> findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndSalaryMinGreaterThanEqual(
            String title, String location, Integer salaryMin);
    List<Vacancy> findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndSalaryMaxLessThanEqual(
            String title, String location, Integer salaryMax);
    List<Vacancy> findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndSalaryMinGreaterThanEqualAndSalaryMaxLessThanEqual(
            String title, String location, Integer salaryMin, Integer salaryMax);
}
package com.example.jobfinder.service;

import com.example.jobfinder.dto.VacancyResponseDTO;
import com.example.jobfinder.entity.Vacancy;
import com.example.jobfinder.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyService {
    @Autowired
    private VacancyRepository vacancyRepository;

    public Vacancy createJob(Vacancy job) {
        return vacancyRepository.save(job);
    }

    public Vacancy updateJob(Long id, Vacancy updatedJob, String email) {
        Vacancy existingJob = vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));
        if (!existingJob.getEmployer().getEmail().equals(email)) {
            throw new RuntimeException("Not authorized to update this vacancy");
        }
        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setSalaryMin(updatedJob.getSalaryMin());
        existingJob.setSalaryMax(updatedJob.getSalaryMax());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setCompany(updatedJob.getCompany());
        return vacancyRepository.save(existingJob);
    }

    public void deleteJob(Long id, String email) {
        Vacancy job = vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));
        if (!job.getEmployer().getEmail().equals(email)) {
            throw new RuntimeException("Not authorized to delete this vacancy");
        }
        vacancyRepository.deleteById(id);
    }

    public VacancyResponseDTO convertToDTO(Vacancy job) {
        VacancyResponseDTO dto = new VacancyResponseDTO();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setSalaryMin(job.getSalaryMin());
        dto.setSalaryMax(job.getSalaryMax());
        dto.setLocation(job.getLocation());
        dto.setCompanyName(job.getCompany() != null ? job.getCompany().getName() : null);
        dto.setCreatedAt(job.getCreatedAt());
        return dto;
    }

    public List<VacancyResponseDTO> convertToDTOList(List<Vacancy> vacancies) {
        return vacancies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<VacancyResponseDTO> getAllJobs() {
        return convertToDTOList(vacancyRepository.findAll());
    }

    public List<VacancyResponseDTO> searchJobs(String title, String location, Integer salaryMin, Integer salaryMax) {
        List<Vacancy> jobs;
        if ((title == null || title.isEmpty()) &&
                (location == null || location.isEmpty()) &&
                salaryMin == null &&
                salaryMax == null) {
            jobs = vacancyRepository.findAll();
        } else if (title != null && !title.isEmpty()) {
            if (location != null && !location.isEmpty()) {
                if (salaryMin != null && salaryMax != null) {
                    jobs = vacancyRepository.findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndSalaryMinGreaterThanEqualAndSalaryMaxLessThanEqual(
                            title, location, salaryMin, salaryMax);
                } else if (salaryMin != null) {
                    jobs = vacancyRepository.findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndSalaryMinGreaterThanEqual(
                            title, location, salaryMin);
                } else if (salaryMax != null) {
                    jobs = vacancyRepository.findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndSalaryMaxLessThanEqual(
                            title, location, salaryMax);
                } else {
                    jobs = vacancyRepository.findByTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(title, location);
                }
            } else if (salaryMin != null && salaryMax != null) {
                jobs = vacancyRepository.findByTitleContainingIgnoreCaseAndSalaryMinGreaterThanEqualAndSalaryMaxLessThanEqual(
                        title, salaryMin, salaryMax);
            } else if (salaryMin != null) {
                jobs = vacancyRepository.findByTitleContainingIgnoreCaseAndSalaryMinGreaterThanEqual(title, salaryMin);
            } else if (salaryMax != null) {
                jobs = vacancyRepository.findByTitleContainingIgnoreCaseAndSalaryMaxLessThanEqual(title, salaryMax);
            } else {
                jobs = vacancyRepository.findByTitleContainingIgnoreCase(title);
            }
        } else if (location != null && !location.isEmpty()) {
            if (salaryMin != null && salaryMax != null) {
                jobs = vacancyRepository.findByLocationContainingIgnoreCaseAndSalaryMinGreaterThanEqualAndSalaryMaxLessThanEqual(
                        location, salaryMin, salaryMax);
            } else if (salaryMin != null) {
                jobs = vacancyRepository.findByLocationContainingIgnoreCaseAndSalaryMinGreaterThanEqual(location, salaryMin);
            } else if (salaryMax != null) {
                jobs = vacancyRepository.findByLocationContainingIgnoreCaseAndSalaryMaxLessThanEqual(location, salaryMax);
            } else {
                jobs = vacancyRepository.findByLocationContainingIgnoreCase(location);
            }
        } else if (salaryMin != null && salaryMax != null) {
            jobs = vacancyRepository.findBySalaryMinGreaterThanEqualAndSalaryMaxLessThanEqual(salaryMin, salaryMax);
        } else if (salaryMin != null) {
            jobs = vacancyRepository.findBySalaryMinGreaterThanEqual(salaryMin);
        } else if (salaryMax != null) {
            jobs = vacancyRepository.findBySalaryMaxLessThanEqual(salaryMax);
        } else {
            jobs = vacancyRepository.findAll();
        }
        return convertToDTOList(jobs);
    }

    public List<VacancyResponseDTO> getJobsByEmployer(Long employerId) {
        List<Vacancy> jobs = vacancyRepository.findByEmployerId(employerId);
        if (jobs.isEmpty()) {
            return List.of();
        }
        return convertToDTOList(jobs);
    }
}
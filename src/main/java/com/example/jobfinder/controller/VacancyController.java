package com.example.jobfinder.controller;

import com.example.jobfinder.dto.VacancyCreateDTO;
import com.example.jobfinder.dto.VacancyResponseDTO;
import com.example.jobfinder.dto.ResponseDTO;
import com.example.jobfinder.entity.Company;
import com.example.jobfinder.entity.Response;
import com.example.jobfinder.entity.User;
import com.example.jobfinder.entity.Vacancy;
import com.example.jobfinder.repository.VacancyRepository;
import com.example.jobfinder.service.CompanyService;
import com.example.jobfinder.service.ResponseService;
import com.example.jobfinder.service.UserService;
import com.example.jobfinder.service.VacancyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import com.example.jobfinder.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class VacancyController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private VacancyService vacancyService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ResponseService responseService;
    @Autowired
    private VacancyRepository vacancyRepository;

    @PostMapping("/employer/jobs")
    public ResponseEntity<VacancyResponseDTO> createJob(@Valid @RequestBody VacancyCreateDTO jobDTO, Authentication authentication) {
        User employerUser = userService.findByEmail(authentication.getName());
        Vacancy job = new Vacancy();
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setSalaryMin(jobDTO.getSalaryMin());
        job.setSalaryMax(jobDTO.getSalaryMax());
        job.setLocation(jobDTO.getLocation());
        job.setCompany(jobDTO.getCompanyId() != null ? companyService.getCompanyById(jobDTO.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found")) : null);
        job.setEmployer(employerUser); // Убедимся, что employer устанавливается
        Vacancy savedJob = vacancyService.createJob(job);
        return ResponseEntity.ok(vacancyService.convertToDTO(savedJob));
    }

    @PutMapping("/employer/jobs/{id}")
    public ResponseEntity<VacancyResponseDTO> updateJob(@PathVariable Long id, @Valid @RequestBody VacancyCreateDTO jobDTO, Authentication authentication) {
        User employerUser = userService.findByEmail(authentication.getName());
        // Загружаем существующую вакансию
        Vacancy existingJob = vacancyService.getVacancyById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));
        // Обновляем только нужные поля
        existingJob.setTitle(jobDTO.getTitle());
        existingJob.setDescription(jobDTO.getDescription());
        existingJob.setSalaryMin(jobDTO.getSalaryMin());
        existingJob.setSalaryMax(jobDTO.getSalaryMax());
        existingJob.setLocation(jobDTO.getLocation());
        existingJob.setCompany(jobDTO.getCompanyId() != null ? companyService.getCompanyById(jobDTO.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found")) : existingJob.getCompany());
        // Вызываем сервис для обновления
        Vacancy savedJob = vacancyService.updateJob(id, existingJob, employerUser.getEmail());
        return ResponseEntity.ok(vacancyService.convertToDTO(savedJob));
    }

    @DeleteMapping("/employer/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id, Authentication authentication) {
        User employerUser = userService.findByEmail(authentication.getName());
        vacancyService.deleteJob(id, employerUser.getEmail());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<VacancyResponseDTO>> getAllJobs() {
        return ResponseEntity.ok(vacancyService.getAllJobs());
    }

    @GetMapping("/jobs/search")
    public ResponseEntity<List<VacancyResponseDTO>> searchJobs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer salaryMin,
            @RequestParam(required = false) Integer salaryMax) {
        return ResponseEntity.ok(vacancyService.searchJobs(title, location, salaryMin, salaryMax));
    }

    @GetMapping("/employer/jobs")
    public ResponseEntity<List<VacancyResponseDTO>> getJobsByApplicant(Authentication authentication) {
        User employerUser = userService.findByEmail(authentication.getName());
        List<VacancyResponseDTO> jobs = vacancyService.getJobsByEmployer(employerUser.getId());
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/applicant/responses")
    public ResponseEntity<ResponseDTO> createResponse(@RequestParam Long jobId, Authentication authentication) {
        ResponseDTO response = responseService.createResponse(jobId, authentication.getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/applicant/responses")
    public ResponseEntity<List<ResponseDTO>> getEmployerResponses(Authentication authentication) {
        User employerUser = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(responseService.getResponsesByApplicant(employerUser.getId()));
    }

    @GetMapping("/employer/responses")
    public ResponseEntity<List<ResponseDTO>> getApplicantResponses(Authentication authentication) {
        User applicantUser = userService.findByEmail(authentication.getName());
        List<Long> jobIds = vacancyRepository.findByEmployerId(applicantUser.getId())
                .stream()
                .map(Vacancy::getId)
                .toList();
        return ResponseEntity.ok(jobIds.stream()
                .flatMap(jobId -> responseService.getResponsesByJob(jobId).stream())
                .toList());
    }

    @GetMapping("/applicant/hasApplied")
    public ResponseEntity<Boolean> hasApplied(@RequestParam Long jobId, Authentication authentication) {
        boolean applied = responseService.hasApplied(jobId, authentication.getName());
        return ResponseEntity.ok(applied);
    }

    @GetMapping("/responses/{id}")
    public ResponseEntity<ResponseDTO> getResponseById(@PathVariable Long id, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        ResponseDTO response = responseService.getResponseById(id);
        if (response == null || (!response.getApplicantId().equals(user.getId()) && !response.getEmployerId().equals(user.getId()))) {
            throw new IllegalArgumentException("Response not found or access denied");
        }
        return ResponseEntity.ok(response);
    }
}
package com.example.jobfinder.service;

import com.example.jobfinder.dto.ResponseDTO;
import com.example.jobfinder.entity.Vacancy;
import com.example.jobfinder.entity.Response;
import com.example.jobfinder.entity.User;
import com.example.jobfinder.repository.VacancyRepository;
import com.example.jobfinder.repository.ResponseRepository;
import com.example.jobfinder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    private ResponseDTO convertToDTO(Response response) {
        ResponseDTO dto = new ResponseDTO();
        dto.setId(response.getId());
        dto.setApplicantEmail(response.getApplicant().getEmail());
        dto.setJobId(response.getJob().getId());
        dto.setJobTitle(response.getJob().getTitle());
        dto.setCreatedAt(response.getCreatedAt());
        return dto;
    }

    public ResponseDTO createResponse(Long jobId, String applicantEmail) {
        User applicant = userRepository.findByEmail(applicantEmail)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
        if (!"EMPLOYER".equals(applicant.getRole().name())) {
            throw new RuntimeException("Only seekers can apply for jobs");
        }
        Vacancy job = vacancyRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (responseRepository.existsByApplicantIdAndJobId(applicant.getId(), jobId)) {
            throw new RuntimeException("You have already applied for this job");
        }

        Response response = new Response();
        response.setApplicant(applicant);
        response.setJob(job);
        response.setCreatedAt(LocalDateTime.now());
        return convertToDTO(responseRepository.save(response));
    }

    public List<ResponseDTO> getResponsesByApplicant(Long applicantId) {
        return responseRepository.findByApplicantId(applicantId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ResponseDTO> getResponsesByJob(Long jobId) {
        return responseRepository.findByJobId(jobId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public boolean hasApplied(Long jobId, String applicantEmail) {
        User applicant = userRepository.findByEmail(applicantEmail)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
        return responseRepository.existsByApplicantIdAndJobId(applicant.getId(), jobId);
    }
}
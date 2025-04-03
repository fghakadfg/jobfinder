package com.example.jobfinder.service;

import com.example.jobfinder.entity.Resume;
import com.example.jobfinder.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

    public Resume createResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    public List<Resume> getResumesByUserId(Long userId) {
        return resumeRepository.findByUserId(userId);
    }
}
package com.example.jobfinder.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobListing job;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @Column(nullable = false)
    private String resume;

    @Column(name = "cover_letter")
    private String coverLetter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;

    @Column(name = "applied_at")
    private LocalDateTime appliedAt = LocalDateTime.now();

    public enum Status {
        PENDING, REVIEWED, REJECTED, ACCEPTED
    }

    // Геттеры
    public Long getId() { return id; }
    public JobListing getJob() { return job; }
    public User getApplicant() { return applicant; }
    public String getResume() { return resume; }
    public String getCoverLetter() { return coverLetter; }
    public Status getStatus() { return status; }
    public LocalDateTime getAppliedAt() { return appliedAt; }

    // Сеттеры
    public void setId(Long id) { this.id = id; }
    public void setJob(JobListing job) { this.job = job; }
    public void setApplicant(User applicant) { this.applicant = applicant; }
    public void setResume(String resume) { this.resume = resume; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
    public void setStatus(Status status) { this.status = status; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }
}
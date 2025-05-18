package com.example.jobfinder.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "responses", uniqueConstraints = @UniqueConstraint(columnNames = {"applicant_id", "job_id"}))
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    private User applicant;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Vacancy job;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getApplicant() { return applicant; }
    public void setApplicant(User applicant) { this.applicant = applicant; }
    public Vacancy getJob() { return job; }
    public void setJob(Vacancy job) { this.job = job; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
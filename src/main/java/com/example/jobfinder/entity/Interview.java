package com.example.jobfinder.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @Column(name = "interview_date", nullable = false)
    private LocalDateTime interviewDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.SCHEDULED;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Status {
        SCHEDULED, COMPLETED, CANCELLED
    }

    // Геттеры
    public Long getId() { return id; }
    public Application getApplication() { return application; }
    public LocalDateTime getInterviewDate() { return interviewDate; }
    public Status getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Сеттеры
    public void setId(Long id) { this.id = id; }
    public void setApplication(Application application) { this.application = application; }
    public void setInterviewDate(LocalDateTime interviewDate) { this.interviewDate = interviewDate; }
    public void setStatus(Status status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
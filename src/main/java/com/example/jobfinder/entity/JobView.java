package com.example.jobfinder.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_views")
public class JobView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Vacancy job;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "viewed_at")
    private LocalDateTime viewedAt = LocalDateTime.now();

    // Геттеры
    public Long getId() { return id; }
    public Vacancy getJob() { return job; }
    public User getUser() { return user; }
    public LocalDateTime getViewedAt() { return viewedAt; }

    // Сеттеры
    public void setId(Long id) { this.id = id; }
    public void setJob(Vacancy job) { this.job = job; }
    public void setUser(User user) { this.user = user; }
    public void setViewedAt(LocalDateTime viewedAt) { this.viewedAt = viewedAt; }
}
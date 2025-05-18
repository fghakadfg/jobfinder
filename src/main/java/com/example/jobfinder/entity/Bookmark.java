package com.example.jobfinder.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookmarks")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Vacancy job;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Геттеры
    public Long getId() { return id; }
    public User getUser() { return user; }
    public Vacancy getJob() { return job; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Сеттеры
    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setJob(Vacancy job) { this.job = job; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
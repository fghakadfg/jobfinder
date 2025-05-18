package com.example.jobfinder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "job_requirements")
public class JobRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Vacancy job;

    @Column(nullable = false)
    private String requirement;

    // Геттеры
    public Long getId() { return id; }
    public Vacancy getJob() { return job; }
    public String getRequirement() { return requirement; }

    // Сеттеры
    public void setId(Long id) { this.id = id; }
    public void setJob(Vacancy job) { this.job = job; }
    public void setRequirement(String requirement) { this.requirement = requirement; }
}
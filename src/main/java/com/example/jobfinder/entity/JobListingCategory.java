package com.example.jobfinder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "job_listing_categories")
public class JobListingCategory {
    @Id
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Vacancy job;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private JobCategory category;

    // Геттеры
    public Vacancy getJob() { return job; }
    public JobCategory getCategory() { return category; }

    // Сеттеры
    public void setJob(Vacancy job) { this.job = job; }
    public void setCategory(JobCategory category) { this.category = category; }
}
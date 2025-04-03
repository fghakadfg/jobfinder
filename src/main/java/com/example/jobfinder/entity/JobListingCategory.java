package com.example.jobfinder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "job_listing_categories")
public class JobListingCategory {
    @Id
    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobListing job;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private JobCategory category;

    // Геттеры
    public JobListing getJob() { return job; }
    public JobCategory getCategory() { return category; }

    // Сеттеры
    public void setJob(JobListing job) { this.job = job; }
    public void setCategory(JobCategory category) { this.category = category; }
}
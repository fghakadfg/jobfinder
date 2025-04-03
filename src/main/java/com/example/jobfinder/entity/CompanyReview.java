package com.example.jobfinder.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "company_reviews")
public class CompanyReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Integer rating;

    private String review;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Геттеры
    public Long getId() { return id; }
    public Company getCompany() { return company; }
    public User getUser() { return user; }
    public Integer getRating() { return rating; }
    public String getReview() { return review; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Сеттеры
    public void setId(Long id) { this.id = id; }
    public void setCompany(Company company) { this.company = company; }
    public void setUser(User user) { this.user = user; }
    public void setRating(Integer rating) { this.rating = rating; }
    public void setReview(String review) { this.review = review; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
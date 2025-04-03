package com.example.jobfinder.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "work_experience")
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String position;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private String description;

    // Геттеры
    public Long getId() { return id; }
    public User getUser() { return user; }
    public String getCompanyName() { return companyName; }
    public String getPosition() { return position; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getDescription() { return description; }

    // Сеттеры
    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setPosition(String position) { this.position = position; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public void setDescription(String description) { this.description = description; }
}
package com.example.jobfinder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String institution;

    @Column(nullable = false)
    private String degree;

    @Column(name = "start_year", nullable = false)
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column(name = "field_of_study")
    private String fieldOfStudy;

    // Геттеры
    public Long getId() { return id; }
    public User getUser() { return user; }
    public String getInstitution() { return institution; }
    public String getDegree() { return degree; }
    public Integer getStartYear() { return startYear; }
    public Integer getEndYear() { return endYear; }
    public String getFieldOfStudy() { return fieldOfStudy; }

    // Сеттеры
    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setInstitution(String institution) { this.institution = institution; }
    public void setDegree(String degree) { this.degree = degree; }
    public void setStartYear(Integer startYear) { this.startYear = startYear; }
    public void setEndYear(Integer endYear) { this.endYear = endYear; }
    public void setFieldOfStudy(String fieldOfStudy) { this.fieldOfStudy = fieldOfStudy; }
}
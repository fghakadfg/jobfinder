package com.example.jobfinder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "message_reads")
public class MessageRead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_read")
    private boolean isRead = false;

    // Геттеры
    public Long getId() { return id; }
    public Message getMessage() { return message; }
    public User getUser() { return user; }
    public boolean isRead() { return isRead; }

    // Сеттеры
    public void setId(Long id) { this.id = id; }
    public void setMessage(Message message) { this.message = message; }
    public void setUser(User user) { this.user = user; }
    public void setRead(boolean read) { this.isRead = read; }
}
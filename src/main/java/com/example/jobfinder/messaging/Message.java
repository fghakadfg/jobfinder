package com.example.jobfinder.messaging;

import com.example.jobfinder.entity.Response;
import com.example.jobfinder.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "response_id")
    private Response response;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "read_by_sender")
    private boolean readBySender = false;

    @Column(name = "read_by_receiver")
    private boolean readByReceiver = false;


    // Геттеры и сеттеры
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }
    public User getReceiver() { return receiver; }
    public void setReceiver(User receiver) { this.receiver = receiver; }
    public Response getResponse() { return response; }
    public void setResponse(Response response) { this.response = response; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
    public boolean isReadBySender() { return readBySender; }
    public void setReadBySender(boolean readBySender) { this.readBySender = readBySender; }
    public boolean isReadByReceiver() { return readByReceiver; }
    public void setReadByReceiver(boolean readByReceiver) { this.readByReceiver = readByReceiver; }
}
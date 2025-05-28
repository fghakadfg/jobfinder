package com.example.jobfinder.chat;

import java.time.LocalDateTime;

public class ChatMessageDTO {
    private Long id;
    private String senderEmail;
    private String receiverEmail;
    private String content;
    private LocalDateTime sentAt;
    private boolean readBySender;
    private boolean readByReceiver;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSenderEmail() { return senderEmail; }
    public void setSenderEmail(String senderEmail) { this.senderEmail = senderEmail; }
    public String getReceiverEmail() { return receiverEmail; }
    public void setReceiverEmail(String receiverEmail) { this.receiverEmail = receiverEmail; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
    public boolean isReadBySender() { return readBySender; }
    public void setReadBySender(boolean readBySender) { this.readBySender = readBySender; }
    public boolean isReadByReceiver() { return readByReceiver; }
    public void setReadByReceiver(boolean readByReceiver) { this.readByReceiver = readByReceiver; }
}
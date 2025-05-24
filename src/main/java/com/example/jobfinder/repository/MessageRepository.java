package com.example.jobfinder.repository;

import com.example.jobfinder.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiverId(Long receiverId);
    List<Message> findByResponseId(Long responseId);
    List<Message> findByResponseIdAndIsReadFalse(Long responseId);
}
package com.example.jobfinder.repository;

import com.example.jobfinder.entity.MessageRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageReadRepository extends JpaRepository<MessageRead, Long> {
    // Можно добавить методы позже, если решим использовать message_reads
}
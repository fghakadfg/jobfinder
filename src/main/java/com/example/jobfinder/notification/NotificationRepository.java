package com.example.jobfinder.notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Notification findByResponseIdAndUserId(Long responseId, Long userId);
}
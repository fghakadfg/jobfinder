package com.example.jobfinder.controller;

import com.example.jobfinder.entity.Notification;
import com.example.jobfinder.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notifications")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.createNotification(notification));
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getNotificationsByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(notificationService.getNotificationsByUserId(userId));
    }
}
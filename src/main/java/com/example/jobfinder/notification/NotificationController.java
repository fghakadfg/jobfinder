package com.example.jobfinder.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import com.example.jobfinder.security.JwtUtil;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/count")
    public Integer getUnreadCount(@RequestParam Long responseId, @RequestParam(required = false) Long userId, HttpServletRequest request) {
        if (userId == null) {
            String token = request.getHeader("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                throw new IllegalArgumentException("No valid Authorization token found");
            }
            userId = jwtUtil.extractId(token);
        }
        return notificationService.getUnreadCount(responseId, userId);
    }
}
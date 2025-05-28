package com.example.jobfinder.notification;

import com.example.jobfinder.messaging.Message;
import com.example.jobfinder.messaging.MessageRepository;
import com.example.jobfinder.entity.User;
import com.example.jobfinder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public Integer getUnreadCount(Long responseId, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) return 0;

        User currentUser = user.get();
        if ("APPLICANT".equals(currentUser.getRole().name())) {
            // Соискатель (APPLICANT): подсчет сообщений от работодателя, где он отправитель и не прочитал ответ
            return (int) messageRepository.findByResponseId(responseId).stream()
                    .filter(m -> m.getSender().getId().equals(userId) && !m.isReadBySender())
                    .count();
        } else if ("EMPLOYER".equals(currentUser.getRole().name())) {
            // Работодатель (EMPLOYER): подсчет сообщений от соискателя, где он получатель и не прочитал
            return (int) messageRepository.findByResponseId(responseId).stream()
                    .filter(m -> m.getReceiver().getId().equals(userId) && !m.isReadByReceiver())
                    .count();
        }
        return 0;
    }

    public void updateNotification(Long responseId, Long userId) {
        Integer unreadCount = getUnreadCount(responseId, userId);
        Notification notification = notificationRepository.findByResponseIdAndUserId(responseId, userId);
        if (notification == null) {
            notification = new Notification();
            notification.setResponseId(responseId);
            notification.setUserId(userId);
        }
        notification.setUnreadCount(unreadCount);
        notificationRepository.save(notification);
    }
}
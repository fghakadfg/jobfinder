package com.example.jobfinder.messaging;

import com.example.jobfinder.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message sendMessage(Message message) {
        message.setSentAt(LocalDateTime.now());
        message.setReadBySender(true); // Отправитель сразу видит как прочитанное
        message.setReadByReceiver(false); // Получатель еще не видел
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByResponseId(Long responseId) {
        return messageRepository.findByResponseId(responseId);
    }

    public void markMessagesAsRead(Long responseId, Long userId) {
        List<Message> messages = messageRepository.findByResponseId(responseId);
        for (Message message : messages) {
            if (message.getReceiver().getId().equals(userId)) {
                message.setReadByReceiver(true); // Получатель отметил как прочитано
            } else if (message.getSender().getId().equals(userId)) {
                message.setReadBySender(true); // Отправитель отметил как прочитано
            }
            messageRepository.save(message);
        }
    }
}
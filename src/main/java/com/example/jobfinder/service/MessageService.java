package com.example.jobfinder.service;

import com.example.jobfinder.entity.Message; import com.example.jobfinder.entity.Response; import com.example.jobfinder.repository.MessageRepository; import com.example.jobfinder.repository.ResponseRepository; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.Service;

import java.time.LocalDateTime; import java.util.List;

@Service public class MessageService { @Autowired private MessageRepository messageRepository;

    @Autowired
    private ResponseRepository responseRepository;

    public Message sendMessage(Message message) {
        if (message.getResponse() == null || message.getResponse().getId() == null) {
            throw new IllegalArgumentException("Response ID is required");
        }

        message.setSentAt(LocalDateTime.now());
        message.setRead(false);

        Message savedMessage = messageRepository.save(message);

        // Обновляем счетчик
        Response response = responseRepository.findById(message.getResponse().getId())
                .orElseThrow(() -> new RuntimeException("Response not found"));
        List<Message> unreadMessages = messageRepository.findByResponseIdAndIsReadFalse(message.getResponse().getId());
        response.setUnreadMessages(unreadMessages.size());
        responseRepository.save(response);

        return savedMessage;
    }

    public List<Message> getMessagesByResponseId(Long responseId) {
        return messageRepository.findByResponseId(responseId);
    }

    public void markMessagesAsRead(Long responseId) {
        System.out.println("Marking messages as read for responseId: " + responseId);
        List<Message> messages = messageRepository.findByResponseIdAndIsReadFalse(responseId);
        System.out.println("Found " + messages.size() + " unread messages for responseId: " + responseId);
        messages.forEach(message -> {
            message.setRead(true);
            System.out.println("Setting isRead to true for messageId: " + message.getId());
        });
        messageRepository.saveAll(messages);
        System.out.println("Saved " + messages.size() + " messages as read for responseId: " + responseId);

        // Обновляем счетчик и синхронизируем
        Response response = responseRepository.findById(responseId)
                .orElseThrow(() -> new RuntimeException("Response not found"));
        List<Message> updatedUnreadMessages = messageRepository.findByResponseIdAndIsReadFalse(responseId);
        response.setUnreadMessages(updatedUnreadMessages.size());
        responseRepository.save(response);
        System.out.println("Updated unread messages count to " + updatedUnreadMessages.size() + " for responseId: " + responseId);
    }

}
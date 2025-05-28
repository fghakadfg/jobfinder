package com.example.jobfinder.chat;

import com.example.jobfinder.messaging.Message;
import com.example.jobfinder.messaging.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private MessageService messageService;

    public List<ChatMessageDTO> getChatMessages(Long responseId) {
        List<Message> messages = messageService.getMessagesByResponseId(responseId);
        return messages.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ChatMessageDTO convertToDTO(Message message) {
        ChatMessageDTO dto = new ChatMessageDTO();
        dto.setId(message.getId());
        dto.setSenderEmail(message.getSender().getEmail());
        dto.setReceiverEmail(message.getReceiver().getEmail());
        dto.setContent(message.getContent());
        dto.setSentAt(message.getSentAt());
        dto.setReadBySender(message.isReadBySender());
        dto.setReadByReceiver(message.isReadByReceiver());
        return dto;
    }
}
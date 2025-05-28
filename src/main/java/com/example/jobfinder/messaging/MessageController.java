package com.example.jobfinder.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/messages")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message savedMessage = messageService.sendMessage(message);
        return ResponseEntity.ok(savedMessage);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessagesByResponseId(@RequestParam Long responseId) {
        List<Message> messages = messageService.getMessagesByResponseId(responseId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/messages/markRead")
    public ResponseEntity<Void> markMessagesAsRead(@RequestBody MarkReadRequest request) {
        if (request.getResponseId() == null || request.getUserId() == null) {
            throw new IllegalArgumentException("responseId and userId cannot be null");
        }
        messageService.markMessagesAsRead(request.getResponseId(), request.getUserId());
        return ResponseEntity.ok().build();
    }
}

class MarkReadRequest {
    private Long responseId;
    private Long userId;

    public Long getResponseId() { return responseId; }
    public void setResponseId(Long responseId) { this.responseId = responseId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
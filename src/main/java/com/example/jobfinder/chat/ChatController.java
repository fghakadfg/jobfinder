package com.example.jobfinder.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/messages")
    public ResponseEntity<List<ChatMessageDTO>> getChatMessages(@RequestParam Long responseId) {
        List<ChatMessageDTO> messages = chatService.getChatMessages(responseId);
        return ResponseEntity.ok(messages);
    }
}
package com.example.jobfinder.controller;

import com.example.jobfinder.entity.Message;
import com.example.jobfinder.service.MessageService;
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
        return ResponseEntity.ok(messageService.sendMessage(message));
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessagesByReceiver(@RequestParam Long receiverId) {
        return ResponseEntity.ok(messageService.getMessagesByReceiverId(receiverId));
    }
}
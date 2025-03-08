package com.sportify.service.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.ChatMessageDTO;
import com.sportify.service.entities.Message;
import com.sportify.service.services.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestParam Long chatRoomId, 
                                               @RequestParam Long senderId, 
                                               @RequestParam String content) {
        Message message = messageService.sendMessage(chatRoomId, senderId, content);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/history/{chatRoomId}")
    public ResponseEntity<List<ChatMessageDTO>> getChatHistory(@PathVariable("chatRoomId") Long chatRoomId) {
        List<Message> messages = messageService.getChatHistory(chatRoomId);
        List<ChatMessageDTO> chatMesDto = messages.stream().map(ChatMessageDTO::new).collect(Collectors.toList()); 
        return ResponseEntity.ok(chatMesDto);
    }
}

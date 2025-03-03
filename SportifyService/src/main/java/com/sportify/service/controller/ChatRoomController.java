package com.sportify.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.ChatRoomDTO;
import com.sportify.service.entities.ChatRoom;
import com.sportify.service.services.ChatRoomService;

@RestController
@RequestMapping("/api/chat-rooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping("/create")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestParam Long userId1, @RequestParam Long userId2) {
        ChatRoom chatRoom = chatRoomService.getOrCreateChatRoom(userId1, userId2);
        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatRoomDTO>> getUserChatRooms(@PathVariable("userId") Long userId) {
        List<ChatRoomDTO> chatRooms = chatRoomService.getUserChatRooms(userId);
        return ResponseEntity.ok(chatRooms);
    }
}


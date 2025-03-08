package com.sportify.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.ChatRoomDTO;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.security.JwtService;
import com.sportify.service.services.ChatRoomService;
import com.sportify.service.services.UserProfileClientService;

@RestController
@RequestMapping("/api/chat")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService; 
    @Autowired
    private JwtService jwtService;
    @Autowired
	private UserProfileClientService userProfileClientService;

    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoomDTO>> getUserChatRooms(@RequestHeader("Authorization") String token) {
    	// Loại bỏ tiền tố "Bearer " trong token
        String jwt = token.replace("Bearer ", "");
        String email = jwtService.extractEmail(jwt);
        UserProfile user = userProfileClientService.findUserByEmail(email);
        List<ChatRoomDTO> chatRooms = chatRoomService.getUserChatRooms(user.getId());
        return ResponseEntity.ok(chatRooms);
    }
}


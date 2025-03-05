package com.sportify.service.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.sportify.service.dtos.ChatMessageDTO;
import com.sportify.service.entities.Message;
import com.sportify.service.services.ChatRoomService;
import com.sportify.service.services.MessageService;

@Controller
public class ChatController {
	
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageService messageService;
    
    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/chat/{chatRoomId}")
    @SendTo("/topic/messages/{chatRoomId}")
    public ChatMessageDTO  sendMessage(@DestinationVariable("chatRoomId") Long chatRoomId, ChatMessageDTO chatMessage) {
        Message message = messageService.sendMessage(chatRoomId, chatMessage.getSenderId(), chatMessage.getContent());
        ChatMessageDTO response = new ChatMessageDTO(
                message.getId(),
                message.getSender().getId(),
                message.getSender().getLastname(),
                message.getContent(),
                message.getSentAt()
            );
        System.out.println("Message sent to topic: " + message.getContent());
        Long receiverId = message.getChatRoom().getUser1().getId().equals(message.getSender().getId()) ? message.getChatRoom().getUser2().getId() : message.getChatRoom().getUser1().getId();
        // Trì hoãn việc gửi danh sách chatrooms 500ms để tránh lỗi WebSocket
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            messagingTemplate.convertAndSend("/topic/chatrooms/" + message.getSender().getId(), 
                                             chatRoomService.getUserChatRooms(message.getSender().getId()));
            messagingTemplate.convertAndSend("/topic/chatrooms/" + receiverId, 
                                             chatRoomService.getUserChatRooms(receiverId));
        }, 500, TimeUnit.MILLISECONDS);
        return response;
    }
}

package com.sportify.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.sportify.service.dtos.ChatMessageDTO;
import com.sportify.service.entities.Message;
import com.sportify.service.services.MessageService;

@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat/{chatRoomId}")
    @SendTo("/topic/messages/{chatRoomId}")
    public ChatMessageDTO  sendMessage(@DestinationVariable("chatRoomId") Long chatRoomId, ChatMessageDTO chatMessage) {
        Message message = messageService.sendMessage(chatRoomId, chatMessage.getSenderId(), chatMessage.getContent());
        return new ChatMessageDTO(
                message.getId(),
                message.getSender().getId(),
                message.getSender().getLastname(),
                message.getContent(),
                message.getSentAt()
            );
    }
}

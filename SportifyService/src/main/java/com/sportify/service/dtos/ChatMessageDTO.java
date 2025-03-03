package com.sportify.service.dtos;

import java.time.LocalDateTime;

import com.sportify.service.entities.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {
	private Long id;
    private Long senderId;
    private String senderName;
    private String content;
    private LocalDateTime sentAt;
    
    public ChatMessageDTO(Message message) {
    	this.id = message.getId();
    	this.senderId = message.getSender().getId();
    	this.senderName = message.getSender().getLastname();
    	this.content = message.getContent();
    	this.sentAt = message.getSentAt();
    			
    }
}

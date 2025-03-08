package com.sportify.service.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.sportify.service.entities.ChatRoom;
import com.sportify.service.entities.Message;
import com.sportify.service.entities.UserProfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDTO {
	private Long roomId;
    private Long user1;
    private Long user2;
    private String lastMessage;

    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean isActive = true;

    public boolean isParticipant(UserProfile user) {
        return user.equals(user1) || user.equals(user2);
    }
    
    public ChatRoomDTO(ChatRoom chatRoom) {
    	this.roomId = chatRoom.getId();
    	this.user1 = chatRoom.getUser1().getId();
    	this.user2 = chatRoom.getUser2().getId();

        // Lấy tin nhắn cuối cùng
        List<Message> messages = chatRoom.getMessages();
        this.lastMessage = (messages.isEmpty()) 
            ? "Chưa có tin nhắn" 
            : messages.get(messages.size() - 1).getContent();
    }
    
}

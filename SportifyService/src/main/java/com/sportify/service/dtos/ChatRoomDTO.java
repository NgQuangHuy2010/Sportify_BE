package com.sportify.service.dtos;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

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
	    private Long user1Id;
	    private Long user2Id;
	    private String lastMessage;
	    private LocalDateTime lastMessageTime;
	    private String otherUserName;
	    private String otherUserAvatar;

    
    public boolean isParticipant(Long userId) {
        return userId.equals(user1Id) || userId.equals(user2Id);
    }

    public ChatRoomDTO(ChatRoom chatRoom, UserProfile currentUser) {
        this.roomId = chatRoom.getId();
        this.user1Id = chatRoom.getUser1().getId();
        this.user2Id = chatRoom.getUser2().getId();

        // Lấy tin nhắn cuối cùng
        Optional<Message> lastMsgOpt = chatRoom.getMessages().stream()
        	    .max(Comparator.comparing(Message::getSentAt));
        	Message lastMsg = lastMsgOpt.orElse(null);
        this.lastMessage = (lastMsg != null) ? lastMsg.getContent() : null;
        this.lastMessageTime = (lastMsg != null) ? lastMsg.getSentAt() : null;

        // Xác định user còn lại trong phòng chat
        UserProfile otherUser = chatRoom.getUser1().equals(currentUser) ? chatRoom.getUser2() : chatRoom.getUser1();
        this.otherUserName = otherUser.getLastname();
        this.otherUserAvatar = otherUser.getAvatar(); 
    }
    
}

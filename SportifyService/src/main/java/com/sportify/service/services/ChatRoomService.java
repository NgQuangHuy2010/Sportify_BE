package com.sportify.service.services;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.ChatRoomDTO;
import com.sportify.service.entities.ChatRoom;
import com.sportify.service.entities.Message;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.repositories.ChatRoomRepository;
import com.sportify.service.repositories.UserProfileRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public ChatRoom getOrCreateChatRoom(Long userId1, Long userId2) {
        UserProfile user1 = userProfileRepository.findById(userId1)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserProfile user2 = userProfileRepository.findById(userId2)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return chatRoomRepository.findByUser1AndUser2(user1, user2)
                .orElseGet(() -> {
                    ChatRoom newRoom = new ChatRoom();
                    newRoom.setUser1(user1);
                    newRoom.setUser2(user2);
                    return chatRoomRepository.save(newRoom);
                });
    }
    public List<ChatRoomDTO> getUserChatRooms(Long userId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findByUser1IdOrUser2Id(userId, userId);

//    	if (chatRooms == null) {
//    	    System.out.println("❌ chatRooms null - Có thể xảy ra lỗi trong service.");
//    	} else if (chatRooms.isEmpty()) {
//    	    System.out.println("⚠️ chatRooms rỗng - Không tìm thấy phòng nào cho userId: " + userId);
//    	} else {
//    	    System.out.println("✅ Số phòng tìm thấy: " + chatRooms.size());
//    	    for (ChatRoom room : chatRooms) {
//    	        System.out.println("Phòng: " + room.getId() + " - ");
//    	    }
//    	}

        return chatRooms.stream()
            .map(chatRoom -> {
                UserProfile otherUser = chatRoom.getUser1().getId().equals(userId) 
                    ? chatRoom.getUser2() 
                    : chatRoom.getUser1();

                Message lastMsg = chatRoom.getMessages().isEmpty() 
                    ? null 
                    : chatRoom.getMessages().get(chatRoom.getMessages().size() - 1);

                String lastMessage = (lastMsg != null) ? lastMsg.getContent() : "";
                LocalDateTime lastMessageTime = (lastMsg != null) ? lastMsg.getSentAt() : null;
                boolean lastMessageIsRead = (lastMsg == null || lastMsg.isRead() || lastMsg.getSender().getId().equals(userId));

                return new ChatRoomDTO(
                    chatRoom.getId(), 
                    chatRoom.getUser1().getId(), 
                    chatRoom.getUser2().getId(), 
                    otherUser.getLastname(), 
                    otherUser.getAvatar(),
                    lastMessage, 
                    lastMessageTime, 
                    lastMessageIsRead
                );
            })
            .sorted(Comparator.comparing(ChatRoomDTO::getLastMessageTime, Comparator.nullsLast(Comparator.reverseOrder()))) // Sắp xếp giảm dần theo thời gian
            .collect(Collectors.toList());
    }

}

package com.sportify.service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.ChatRoomDTO;
import com.sportify.service.entities.ChatRoom;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.repositories.ChatRoomRepository;
import com.sportify.service.repositories.UserProfileRepository;

@Service
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
        UserProfile user = userProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<ChatRoom> chatrooms =  chatRoomRepository.findAllByUser(user);
        return chatrooms.stream().map(ChatRoomDTO::new).collect(Collectors.toList());
    }
}

package com.sportify.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.entities.ChatRoom;
import com.sportify.service.entities.Message;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.repositories.ChatRoomRepository;
import com.sportify.service.repositories.MessageRepository;
import com.sportify.service.repositories.UserProfileRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public Message sendMessage(Long chatRoomId, Long senderId, String content) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        UserProfile sender = userProfileRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Message message = new Message();
        message.setChatRoom(chatRoom);
        message.setSender(sender);
        message.setContent(content);
        return messageRepository.save(message);
    }

    public List<Message> getChatHistory(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));
        return messageRepository.findByChatRoomOrderBySentAtAsc(chatRoom);
    }

    // Cập nhật trạng thái đã đọc khi user mở phòng chat
    public void markMessagesAsRead(Long userId, Long chatRoomId) {
        messageRepository.markMessagesAsRead(userId, chatRoomId);
    }

    // Lấy số tin nhắn chưa đọc của user trong mỗi phòng chat
    public long getUnreadMessageCount(Long userId, Long chatRoomId) {
        return messageRepository.countUnreadMessages(userId, chatRoomId);
    }
}

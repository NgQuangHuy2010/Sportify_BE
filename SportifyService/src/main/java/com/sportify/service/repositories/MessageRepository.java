package com.sportify.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sportify.service.entities.ChatRoom;
import com.sportify.service.entities.Message;

import jakarta.transaction.Transactional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByChatRoomOrderBySentAtAsc(ChatRoom chatRoom);

    // Lấy tất cả tin nhắn trong phòng chat
    List<Message> findByChatRoomIdOrderBySentAtAsc(Long chatRoomId);

    // Lấy tất cả tin nhắn chưa đọc trong phòng chat dành cho user
    @Query("SELECT m FROM Message m WHERE m.chatRoom.id = :chatRoomId AND m.sender.id <> :userId AND m.isRead = false")
    List<Message> findUnreadMessages(Long userId, Long chatRoomId);

  
    @Transactional
    @Modifying
    @Query("UPDATE Message m SET m.isRead = true WHERE m.chatRoom.id = :chatRoomId AND m.sender.id <> :userId AND m.isRead = false")
    void markMessagesAsRead(@Param("chatRoomId") Long chatRoomId, @Param("userId") Long userId);

    // Đếm số tin nhắn chưa đọc trong mỗi phòng chat của user
    @Query("SELECT COUNT(m) FROM Message m WHERE m.chatRoom.id = :chatRoomId AND m.sender.id <> :userId AND m.isRead = false")
    long countUnreadMessages(@Param("chatRoomId") Long chatRoomId, @Param("userId") Long userId);
}

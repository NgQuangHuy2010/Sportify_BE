package com.sportify.service.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sportify.service.entities.ChatRoom;
import com.sportify.service.entities.UserProfile;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    
    Optional<ChatRoom> findByUser1AndUser2(UserProfile user1, UserProfile user2);
    List<ChatRoom> findByUser1IdOrUser2Id(Long user1Id, Long user2Id);
    
    @Query("SELECT c FROM ChatRoom c WHERE c.user1 = :user OR c.user2 = :user")
    List<ChatRoom> findAllByUser(@Param("user") UserProfile user);
}

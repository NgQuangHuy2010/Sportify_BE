package com.sportify.service.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "chatrooms")
public class ChatRoom extends AbstractEntity {

	private static final long serialVersionUID = -2248018377055415827L;

//	@Column(name = "room_name", length = 255, nullable = true)
//	private String roomName;
//
//	@Column(name = "is_group", nullable = false)
//	private Boolean isGroup;

//	@Column(name = "created_at")
//	private LocalDateTime createdAt;
	
	@ManyToOne
    @JoinColumn(name = "user1_id")
    private UserProfile user1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private UserProfile user2;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "is_active")
    private boolean isActive = true;

    public boolean isParticipant(UserProfile user) {
        return user.equals(user1) || user.equals(user2);
    }
}

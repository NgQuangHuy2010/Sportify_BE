package com.sportify.service.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.sportify.service.enums.MessageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "messages")
public class Message extends AbstractEntity {

	private static final long serialVersionUID = -1796327887997862453L;
	
	
	@ManyToOne()
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;
	
	@ManyToOne()
    @JoinColumn(name = "sender_id")
    private UserProfile sender;
	
	@OneToMany(mappedBy = "message")
	private List<MessageReadStatus> readStatusList;

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type")
    private MessageType messageType;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;
    


}

package com.sportify.service.entities;

import java.sql.Timestamp;

import com.sportify.service.enums.MessageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message extends AbstractEntity {

	private static final long serialVersionUID = -1796327887997862453L;
	

    @Column(name = "room_id")
    private int roomId;

    @Column(name = "user_sender_id")
    private int userSenderId;

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type")
    private MessageType messageType;

    @Column(name = "sent_at")
    private Timestamp sentAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;


}

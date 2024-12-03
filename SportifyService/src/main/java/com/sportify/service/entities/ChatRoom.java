package com.sportify.service.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ChatRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "room_name", length = 255, nullable = true)
	private String roomName;

	@Column(name = "is_group", nullable = false)
	private Boolean isGroup;

	@Column(name = "created_at")
	private LocalDateTime createdAt;
}

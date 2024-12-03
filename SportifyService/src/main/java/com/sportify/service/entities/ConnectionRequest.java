package com.sportify.service.entities;

import java.sql.Timestamp;

import com.sportify.service.enums.ConnectionRequestStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "connection_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionRequest extends AbstractEntity {

	private static final long serialVersionUID = -5529531112980901363L;

	@Column(name = "sender_id")
	private int senderId;

	@Column(name = "receiver_id")
	private int receiverId;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private ConnectionRequestStatus status;

	@Column(name = "sent_at")
	private Timestamp sentAt;

	@Column(name = "responded_at")
	private Timestamp respondedAt;
}

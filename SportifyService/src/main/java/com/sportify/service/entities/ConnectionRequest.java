package com.sportify.service.entities;

import java.time.LocalDateTime;

import com.sportify.service.enums.ConnectionRequestStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "connection_requests")
public class ConnectionRequest extends AbstractEntity {

	private static final long serialVersionUID = -5529531112980901363L;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private UserProfile sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private UserProfile receiver;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private ConnectionRequestStatus status;

	@Column(name = "sent_at")
	private LocalDateTime sentAt;

	@Column(name = "responded_at")
	private LocalDateTime respondedAt;
}

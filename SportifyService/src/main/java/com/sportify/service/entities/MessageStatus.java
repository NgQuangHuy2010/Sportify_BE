package com.sportify.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "message_status")
public class MessageStatus extends AbstractEntity {
	
	private static final long serialVersionUID = 9010493806619953651L;
	
	@Column(name = "is_read")
    private boolean isRead;

}

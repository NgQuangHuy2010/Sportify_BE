//package com.sportify.service.entities;
//
//
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//@Entity
//@Table(name = "message_status")
//public class MessageReadStatus extends AbstractEntity {
//	
//	private static final long serialVersionUID = 9010493806619953651L;
//	
//	@ManyToOne()
//	@JoinColumn(name = "receiver_id")
//	private UserProfile receiver;
//	
//	@ManyToOne()
//	@JoinColumn(name = "message_id")
//	private Message message;
//	
//	@Column(name = "is_read")
//    private boolean isRead;
//
//}

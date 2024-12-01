package com.sportify.service.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class UserAccount extends AbstractEntity {

	private static final long serialVersionUID = -7382915357775510423L;

	private String username;
	
	private String password;
	
	private LocalDateTime lastLogin;
	
	private String registrationMethod;
}

package com.sportify.service.entities;

import java.time.LocalDateTime;
import java.util.Set;

import com.sportify.service.enums.Gender;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class UserProfile extends AbstractEntity{
	private static final long serialVersionUID = 469702593221707920L;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private LocalDateTime birthday;
	
	private String phone;
	
	private String avatar;
	
	private String bio;
	
	private Gender gender;
	
	@OneToOne
	private Address address;
	
	@ManyToMany(mappedBy = "userProfiles")
	private Set<Sport> sports;

}

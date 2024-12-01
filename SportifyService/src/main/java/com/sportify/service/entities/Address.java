package com.sportify.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Address extends AbstractEntity{
	
	private static final long serialVersionUID = -3570054271006418893L;

	private String  ward;
	
	private String district;
	
	private String city;
	
	private String no;
	
	@OneToOne
	private UserProfile userprofile;
}

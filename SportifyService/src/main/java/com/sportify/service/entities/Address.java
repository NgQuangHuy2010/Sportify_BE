package com.sportify.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Address extends AbstractEntity {

	private static final long serialVersionUID = -3570054271006418893L;

	@Column(name = "ward")
	private String ward;

	@Column(name = "district")
	private String district;

	@Column(name = "city")
	private String city;

	@Column(name = "no")
	private String no;

	@OneToOne
	private UserProfile userprofile;
}

package com.sportify.service.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Sport extends AbstractEntity {

	private static final long serialVersionUID = 5216464673846909313L;

	private String sportName;
	
	private String image;
	
	@ManyToMany(mappedBy = "sports")
	private Set<UserProfile> userprofiles;
}

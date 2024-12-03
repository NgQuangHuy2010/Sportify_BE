package com.sportify.service.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "gender")
public class Gender extends AbstractEntity {

	private static final long serialVersionUID = 6841891247455695637L;
	@Column(name = "gender")
    private String gender;
	
	@OneToMany(mappedBy = "gender")
	private List<UserProfile> userProfiles;
}

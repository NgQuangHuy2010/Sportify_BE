package com.sportify.service.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
@Table(name = "sports")
public class Sport extends AbstractEntity {

	private static final long serialVersionUID = 5216464673846909313L;

	@Column(name = "sport_name", nullable = false)
	private String sportName;
	
	private String image;
	
	@ManyToMany(mappedBy = "sports")
	private Set<UserProfile> userProfiles = new HashSet<>();
}

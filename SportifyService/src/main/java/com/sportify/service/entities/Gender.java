package com.sportify.service.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
@Table(name = "gender")
public class Gender extends AbstractEntity {

	private static final long serialVersionUID = 6841891247455695637L;
	@Column(name = "gender")
    private String gender;
	
	@OneToMany(mappedBy = "gender",fetch = FetchType.LAZY)
	private List<UserProfile> userProfiles;
}

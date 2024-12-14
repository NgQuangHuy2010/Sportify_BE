package com.sportify.service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "address")
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

	@OneToOne()
	@JoinColumn(name = "user_id")
	private UserProfile userProfile;
}

package com.sportify.service.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user_profiles")
public class UserProfile extends AbstractEntity {
	private static final long serialVersionUID = 469702593221707920L;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "email")
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "phone")
	private String phone;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "bio")
	private String bio;

	// Relationship:

	@ManyToOne()
	@JoinColumn(name = "gender_id")
	private Gender gender;

	@OneToOne(mappedBy = "userProfile")
	private Address address;

	@OneToOne(mappedBy = "userProfile")
	private UserAccount userAccount;

	@OneToOne(mappedBy = "userProfile")
	private ConnectSetting connectSetting;

	@OneToMany(mappedBy = "userProfile")
	private List<Feedback> feedbacks;

	@OneToMany(mappedBy = "sender")
	private List<ConnectionRequest> sentRequests;

	@OneToMany(mappedBy = "receiver")
	private List<ConnectionRequest> receivedRequests;

	@ManyToMany
	@JoinTable(name = "users_sports", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "sport_id"))
	private Set<Sport> sports = new HashSet<>();

}

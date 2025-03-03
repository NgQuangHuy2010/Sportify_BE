package com.sportify.service.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sportify.service.enums.Gender;
import com.sportify.service.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

	@Column(name = "is_locked")
	private Boolean isLocked = false;

	// Relationship:

	@Enumerated(EnumType.STRING) 
	@Column(name = "gender", nullable = true)
	private Gender gender;

	@Enumerated(EnumType.STRING) 
	@Column(name = "role", nullable = false)
	private Role role;

	@OneToOne(mappedBy = "userProfile")
	@JsonManagedReference
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
	private List<Sport> sports = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Booking> bookings = new ArrayList<>();
	
	//Chat:

    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatRoom> chatRoomsAsUser1 = new ArrayList<>();

    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatRoom> chatRoomsAsUser2 = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> sentMessages = new ArrayList<>();

    @Column(name = "last_active")
    private LocalDateTime lastActive = LocalDateTime.now();

}

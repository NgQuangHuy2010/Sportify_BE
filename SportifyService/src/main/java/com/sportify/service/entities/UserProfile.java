package com.sportify.service.entities;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class UserProfile extends AbstractEntity{
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
    private long phone;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "bio")
    private String bio;

    @ManyToOne(fetch = FetchType.EAGER)
	private Gender gender;
	
	@OneToOne
	private Address address;
	
	@ManyToMany(mappedBy = "userProfiles")
	private Set<Sport> sports;

}

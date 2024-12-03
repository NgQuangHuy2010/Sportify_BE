package com.sportify.service.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_accounts")
public class UserAccount extends AbstractEntity {

	private static final long serialVersionUID = -7382915357775510423L;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_login")
    private Timestamp lastLogin;

    @Column(name = "registration_method")
    private String registrationMethod;
}

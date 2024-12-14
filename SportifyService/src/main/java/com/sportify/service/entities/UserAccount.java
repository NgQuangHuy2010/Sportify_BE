package com.sportify.service.entities;

import java.time.LocalDateTime;

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
@Table(name = "user_accounts")
public class UserAccount extends AbstractEntity {

	private static final long serialVersionUID = -7382915357775510423L;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "registration_method")
    private String registrationMethod;
    
    //Relationship:
    @OneToOne()
	@JoinColumn(name = "user_id")
	private UserProfile userProfile;
}

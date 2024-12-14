package com.sportify.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sportify.service.entities.UserProfile;
import com.sportify.service.services.UserProfileService;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserTest {
	
	@Autowired
	TestEntityManager entityManager;
	@Autowired
	UserProfileService userProService;
	
	@Test
	public void createUserProfiles() {
		for (int i = 1; i <11; i ++) {
			UserProfile user = new UserProfile();
			user.setFirstname("User" + i);
			userProService.addUserProfile(user);
		}
	}
}

package com.sportify.service;

import java.util.List;

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
public class DataAccessTest {
	@Autowired
	TestEntityManager entityManager;
	@Autowired
	UserProfileService userProService;
	
	@Test
	public void getUserPro() {
		List<UserProfile> userList = userProService.getRecommendUsersToConnect();
		
		for (UserProfile user : userList) {
			System.out.println("Name: " + user.getFirstname());
		}
	}
}

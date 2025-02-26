package com.sportify.service;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.sportify.service.entities.UserProfile;
import com.sportify.service.enums.Gender;
import com.sportify.service.repositories.UserProfileRepository;

//@DataJpaTest(showSql = true)
@SpringBootTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserTest {
	
	@Autowired
	UserProfileRepository userRepo;
	
	@Test
	public void createUserProfiles() {
		for (int i = 32; i <61; i ++) {
			UserProfile user = new UserProfile();
			user.setFirstname("First" + i);
			user.setLastname("Last" +i );
			user.setEmail("defaultemail" + i + "@gmail.com");
			user.setGender(Gender.MALE);
			userRepo.save(user);
		}
	}
}

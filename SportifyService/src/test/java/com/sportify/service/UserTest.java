package com.sportify.service;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.sportify.service.entities.Address;
import com.sportify.service.entities.ConnectSetting;
import com.sportify.service.entities.UserAccount;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.enums.Gender;
import com.sportify.service.repositories.AddressClientRepository;
import com.sportify.service.repositories.ConnectSettingRepository;
import com.sportify.service.repositories.UserAccountRepository;
import com.sportify.service.repositories.UserProfileRepository;

//@DataJpaTest(showSql = true)
@SpringBootTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserTest {
	
	@Autowired
	private UserProfileRepository userRepo;
	@Autowired
	private UserAccountRepository accRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ConnectSettingRepository conRepo;
	@Autowired
	private AddressClientRepository addressRepo;
	
	@Test
	public void createUser() {
		for (int i = 0; i <=20; i ++) {
			UserProfile user = new UserProfile();
			user.setFirstname("First" + i);
			user.setLastname("Last" +i );
			user.setEmail("user" + i + "@gmail.com");
			user.setGender(Gender.MALE);
			userRepo.save(user);

//	        UserAccount userAccount = new UserAccount();
//	        userAccount.setUsername("user" + i);
//	        userAccount.setEmail("user" + i + "@gmail.com");
//	        userAccount.setPassword(passwordEncoder.encode("123456"));
//	        userAccount.setRegistrationMethod("Manual");
//	        userAccount.setUserProfile(user);
//	        accRepo.save(userAccount);
//	        
//	        Address address = new Address();
//	        address.setCity("HCM");
//	        address.setDistrict("GV");
//	        address.setWard("11");
//	        address.setUserProfile(user);
//	        addressRepo.save(address);
//	        
//	        ConnectSetting con = new ConnectSetting();
//	        con.setStatus(0);
//	        con.setUserProfile(user);
//	        con.setGenderFind("MALE");
//	        conRepo.save(con);
		}
//	
//		for (int i = 21; i <= 41; i ++) {
//			UserProfile user = new UserProfile();
//			user.setFirstname("First" + i);
//			user.setLastname("Last" +i );
//			user.setEmail("user" + i + "@gmail.com");
//			user.setGender(Gender.MALE);
//			userRepo.save(user);
//
//	        UserAccount userAccount = new UserAccount();
//	        userAccount.setUsername("user" + i);
//	        userAccount.setEmail("user" + i + "@gmail.com");
//	        userAccount.setPassword(passwordEncoder.encode("123456"));
//	        userAccount.setRegistrationMethod("Manual");
//	        userAccount.setUserProfile(user);
//	        accRepo.save(userAccount);
//	        
//	        Address address = new Address();
//	        address.setCity("HN");
//	        address.setDistrict("HD");
//	        address.setWard("10");
//	        address.setUserProfile(user);
//	        addressRepo.save(address);
//	        
//	        ConnectSetting con = new ConnectSetting();
//	        con.setStatus(0);
//	        con.setUserProfile(user);
//	        con.setGenderFind("MALE");
//	        conRepo.save(con);
//		}
	}
}

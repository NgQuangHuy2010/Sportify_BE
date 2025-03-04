package com.sportify.service.seed;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sportify.service.dtos.SportDTO;
import com.sportify.service.entities.Address;
import com.sportify.service.entities.ConnectSetting;
import com.sportify.service.entities.Sport;
import com.sportify.service.entities.UserAccount;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.enums.Gender;
import com.sportify.service.enums.Role;
import com.sportify.service.repositories.AddressClientRepository;
import com.sportify.service.repositories.ConnectSettingRepository;
import com.sportify.service.repositories.UserAccountRepository;
import com.sportify.service.repositories.UserProfileRepository;
import com.sportify.service.repositories.admin.Admin_SportRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final UserProfileRepository userProfileRepository;
    private final UserAccountRepository userAccountRepository;
    private final AddressClientRepository addressRepository;
    private final ConnectSettingRepository connectRepository;
    private final Admin_SportRepository sportRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (sportRepository.count() == 0) {
        	seedSports();
        }
        if (userProfileRepository.count() == 0) {
            seedUsers();
        }
    }

    private void seedSports() {
        List<SportDTO> sports = Arrays.asList(
                new SportDTO("Soccer", "icon_soccer.png"),
                new SportDTO("Basketball", "icon_basketball.png"),
                new SportDTO("Tennis", "icon_tennis.png"),
                new SportDTO("Badminton", "icon_badminton.png"),
                new SportDTO("Chess", "icon_chess.png"),
                new SportDTO("Swimming", "icon_swimming.png"),
                new SportDTO("Volleyball", "icon_volleyball.png"),
                new SportDTO("Pickleball", "icon_pickleball.png")
        );
        List<Sport> sportEntities = new ArrayList<>();
        	    for (SportDTO sportDTO : sports) {
        	        Sport sport = new Sport();
        	        sport.setSportName(sportDTO.getSportName());
        	        sport.setImage(sportDTO.getImageUrl());
        	        sportEntities.add(sport);
        	    }

        	    sportRepository.saveAll(sportEntities);
    }

	
	private void seedUsers() {
	    List<Sport> sports = sportRepository.findAll();
	    Random random = new Random();
	    // Giới hạn thời gian từ 01/01/2024 đến hiện tại
	    LocalDateTime start2024 = LocalDateTime.of(2024, 1, 1, 0, 0);
	    LocalDateTime now = LocalDateTime.now();
        // Tạo ngày tạo ngẫu nhiên trong khoảng 2024 - 2025
        long daysBetween = ChronoUnit.DAYS.between(start2024, now);
	
	    for (int i = 1; i <= 500; i++) {
	        LocalDateTime randomCreatedOn = start2024.plusDays(random.nextInt((int) daysBetween));
	
	        // Tạo UserProfile
	        UserProfile userProfile = new UserProfile();
	        userProfile.setFirstname("User" + i);
	        userProfile.setLastname("Test" + i);
	        userProfile.setEmail("user" + i + "@gmail.com");
	        userProfile.setPhone("098765432" + i);
	        userProfile.setBio("This is a sample bio for User " + i);
	        userProfile.setGender(Gender.MALE);
	        userProfile.setRole(i == 1 ? Role.ADMIN : Role.USER);
	        userProfile.setAvatar("user" + i + ".jpg");
	        userProfile.setSports(sports.subList(0, (i % sports.size()) + 1));
	        userProfile.setCreatedOn(randomCreatedOn); // Gán ngày tạo ngẫu nhiên
	        userProfile.setUpdatedOn(randomCreatedOn);
	        userProfileRepository.save(userProfile);
	
	        // Tạo UserAccount
	        UserAccount userAccount = new UserAccount();
	        userAccount.setUsername("user" + i);
	        userAccount.setEmail(userProfile.getEmail());
	        userAccount.setPassword(passwordEncoder.encode("123456"));
	        userAccount.setRegistrationMethod("email");
	        userAccount.setUserProfile(userProfile);
	        userAccount.setCreatedOn(randomCreatedOn);
	        userAccount.setUpdatedOn(randomCreatedOn);
	        userAccountRepository.save(userAccount);
	
	        // Tạo Address
	        Address address = new Address();
	        address.setWard("Ward " + (i % 10));
	        address.setDistrict("District " + (i % 5));
	        address.setCity(i % 2 == 0 ? "Hanoi" : "Ho Chi Minh");
	        address.setNo("321 Street " + i);
	        address.setUserProfile(userProfile);
	        address.setCreatedOn(randomCreatedOn);
	        address.setUpdatedOn(randomCreatedOn);
	        addressRepository.save(address);
	
	        // Tạo ConnectSetting
	        ConnectSetting con = new ConnectSetting();
	        con.setStatus(0);
	        con.setUserProfile(userProfile);
	        con.setGenderFind("MALE");
	        con.setCreatedOn(randomCreatedOn);
	        con.setUpdatedOn(randomCreatedOn);
	        connectRepository.save(con);
	    }
	}
}

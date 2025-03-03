package com.sportify.service.seed;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
        if (userProfileRepository.count() == 0) {
//            seedSports();
            seedUsers();
        }
    }

//    private void seedSports() {
//        List<Sport> sports = Arrays.asList(
//                new Sport("Football", "uploads/sports/football.png"),
//                new Sport("Basketball", "uploads/sports/basketball.png"),
//                new Sport("Tennis", "uploads/sports/tennis.png"),
//                new Sport("Badminton", "uploads/sports/badminton.png")
//        );
//        sportRepository.saveAll(sports);
//    }

    private void seedUsers() {
        List<Sport> sports = sportRepository.findAll();

        for (int i = 1; i <= 50; i++) {
            // Tạo UserProfile
            UserProfile userProfile = new UserProfile();
            userProfile.setFirstname("User" + i);
            userProfile.setLastname("Test" + i);
            userProfile.setEmail("user" + i + "@gmail.com");
            userProfile.setPhone("098765432" + i);
            userProfile.setBio("This is a sample bio for User " + i);
            userProfile.setGender(Gender.MALE);
            userProfile.setRole(i == 1 ? Role.ADMIN : Role.USER);
            userProfile.setAvatar("uploads/avatars/user" + i + ".png");

            // Chọn ngẫu nhiên một số môn thể thao
            userProfile.setSports(sports.subList(0, (i % sports.size()) + 1));

            userProfileRepository.save(userProfile);

            // Tạo UserAccount
            UserAccount userAccount = new UserAccount();
            userAccount.setUsername("user" + i);
            userAccount.setEmail(userProfile.getEmail());
            userAccount.setPassword(passwordEncoder.encode("123456"));
            userAccount.setRegistrationMethod("email");
            userAccount.setUserProfile(userProfile);
            userAccountRepository.save(userAccount);

            // Tạo Address
            Address address = new Address();
            address.setWard("Ward " + (i % 10));
            address.setDistrict("District " + (i % 5));
            address.setCity(i % 2 == 0 ? "Hanoi" : "Ho Chi Minh");
            address.setNo("123 Street " + i);
            address.setUserProfile(userProfile);
            addressRepository.save(address);
            
            ConnectSetting con = new ConnectSetting();
	        con.setStatus(0);
	        con.setUserProfile(userProfile);
	        con.setGenderFind("MALE");
	        connectRepository.save(con);
        }
    }
}

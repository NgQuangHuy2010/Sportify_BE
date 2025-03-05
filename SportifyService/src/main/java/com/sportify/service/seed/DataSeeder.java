package com.sportify.service.seed;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sportify.service.dtos.SportDTO;
import com.sportify.service.entities.Address;
import com.sportify.service.entities.ChatRoom;
import com.sportify.service.entities.ConnectSetting;
import com.sportify.service.entities.ConnectionRequest;
import com.sportify.service.entities.Sport;
import com.sportify.service.entities.UserAccount;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.enums.ConnectionRequestStatus;
import com.sportify.service.enums.Gender;
import com.sportify.service.enums.Role;
import com.sportify.service.repositories.AddressClientRepository;
import com.sportify.service.repositories.ConnectSettingRepository;
import com.sportify.service.repositories.ConnectionRequestRepository;
import com.sportify.service.repositories.UserAccountRepository;
import com.sportify.service.repositories.UserProfileRepository;
import com.sportify.service.repositories.admin.Admin_SportRepository;
import com.sportify.service.services.ChatRoomService;
import com.sportify.service.services.MessageService;

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
    private final ConnectionRequestRepository connectionRequestRepository;
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    

    @Override
    public void run(String... args) throws Exception {
        if (sportRepository.count() == 0) {
        	seedSports();
        }
        if (userProfileRepository.count() == 0) {
            seedUsers();
        }
        if (connectionRequestRepository.count() == 0) {
            seedConnections();
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
        long daysBetween = ChronoUnit.DAYS.between(start2024, now);

        // Giới hạn năm sinh từ 1970 đến 2005
        int minYear = 1970;
        int maxYear = 2005;

        for (int i = 1; i <= 500; i++) {
            // Ngày tạo ngẫu nhiên từ 2024 đến hiện tại
            LocalDateTime randomCreatedOn = start2024.plusDays(random.nextInt((int) daysBetween + 1));

            // Ngày sinh ngẫu nhiên từ 1970 đến 2005
            int randomYear = minYear + random.nextInt(maxYear - minYear + 1);
            int randomMonth = random.nextInt(12); // Tháng từ 0 đến 11
            int randomDay = random.nextInt(28) + 1; // Ngày từ 1 đến 28 (để tránh lỗi tháng ngắn)

            Calendar calendar = Calendar.getInstance();
            calendar.set(randomYear, randomMonth, randomDay);
            Date randomBirthday = calendar.getTime();

            // Tạo UserProfile
            UserProfile userProfile = new UserProfile();
            userProfile.setFirstname("User" + i);
            userProfile.setLastname("Test" + i);
            userProfile.setEmail("user" + i + "@gmail.com");
            userProfile.setPhone("098765432" + i);
            userProfile.setBio("This is a sample bio for User " + i);
            userProfile.setGender(Gender.MALE);
            userProfile.setRole(i == 1 ? Role.ADMIN : Role.USER);
            userProfile.setAvatar(i >= 11 ? "default_avatar.jpg" : "user" + i + ".jpg");
            userProfile.setSports(sports.subList(0, (i % sports.size()) + 1));
            userProfile.setBirthday(randomBirthday); // Gán ngày sinh ngẫu nhiên
            userProfile.setCreatedOn(randomCreatedOn);
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
    
    private void seedConnections() {
    	 List<UserProfile> users = userProfileRepository.findAll().stream()
    	            .sorted(Comparator.comparing(UserProfile::getId))
    	            .limit(10) // Lấy 10 user đầu tiên
    	            .collect(Collectors.toList());

    	    if (users.size() < 10) {
    	        throw new RuntimeException("Không đủ 10 user để tạo kết nối!");
    	    }

    	    List<UserProfile> senders = users.subList(0, 5);  // User 1 đến 5
    	    List<UserProfile> receivers = users.subList(5, 10); // User 6 đến 10

    	    for (UserProfile sender : senders) {
    	        for (UserProfile receiver : receivers) {
    	            // Kiểm tra xem đã có yêu cầu trước đó chưa
    	            if (connectionRequestRepository.findBySenderAndReceiver(sender, receiver).isPresent()) {
    	                continue;
    	            }

    	            // Gửi yêu cầu kết bạn
    	            ConnectionRequest request = new ConnectionRequest();
    	            request.setSender(sender);
    	            request.setReceiver(receiver);
    	            request.setStatus(ConnectionRequestStatus.PENDING);
    	            request.setSentAt(LocalDateTime.now());
    	            connectionRequestRepository.save(request);

    	            // Chấp nhận kết bạn
    	            request.setStatus(ConnectionRequestStatus.ACCEPTED);
    	            request.setRespondedAt(LocalDateTime.now());
    	            connectionRequestRepository.save(request);

    	            // Tạo ChatRoom
    	            ChatRoom chatRoom = chatRoomService.getOrCreateChatRoom(sender.getId(), receiver.getId());
    	            messageService.sendMessage(chatRoom.getId(), receiver.getId(), "We are friends now!");
    	        }
    	    }
    }


}

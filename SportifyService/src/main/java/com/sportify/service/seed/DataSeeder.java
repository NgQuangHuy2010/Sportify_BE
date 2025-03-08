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
    private final LocationService locationService;
    

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

    // 🛠️ Tạo 5 tài khoản admin trước
    for (int i = 1; i <= 5; i++) {
        createUser(i, Role.ADMIN, sports, start2024, daysBetween, minYear, maxYear, random);
    }

    // 🛠️ Tạo 495 user còn lại
    for (int i = 6; i <= 500; i++) {
        createUser(i, Role.USER, sports, start2024, daysBetween, minYear, maxYear, random);
    }
}

private void createUser(int i, Role role, List<Sport> sports, LocalDateTime start2024, long daysBetween, int minYear, int maxYear, Random random) {
    LocalDateTime randomCreatedOn = start2024.plusDays(random.nextInt((int) daysBetween + 1));
    int randomYear = minYear + random.nextInt(maxYear - minYear + 1);
    int randomMonth = random.nextInt(12);
    int randomDay = random.nextInt(28) + 1;

    Calendar calendar = Calendar.getInstance();
    calendar.set(randomYear, randomMonth, randomDay);
    Date randomBirthday = calendar.getTime();

    String username = (role == Role.ADMIN ? "admin" : "user") + i;
    String email = username + "@gmail.com";

    // Tạo UserProfile
    UserProfile userProfile = new UserProfile();
    userProfile.setFirstname("User" + i);
    userProfile.setLastname("Test" + i);
    userProfile.setEmail(email);
    userProfile.setPhone("098765432" + i);
    userProfile.setBio("This is a sample bio for " + username);
    userProfile.setGender(Gender.MALE);
    userProfile.setRole(role);
    userProfile.setAvatar(i >= 11 ? "default_avatar.jpg" : username + ".jpg");
    userProfile.setSports(sports.subList(0, (i % sports.size()) + 1));
    userProfile.setBirthday(randomBirthday);
    userProfile.setCreatedOn(randomCreatedOn);
    userProfile.setUpdatedOn(randomCreatedOn);
    userProfileRepository.save(userProfile);

    // Tạo UserAccount
    UserAccount userAccount = new UserAccount();
    userAccount.setUsername(username);
    userAccount.setEmail(email);
    userAccount.setPassword(passwordEncoder.encode("123456"));
    userAccount.setRegistrationMethod("email");
    userAccount.setUserProfile(userProfile);
    userAccount.setCreatedOn(randomCreatedOn);
    userAccount.setUpdatedOn(randomCreatedOn);
    userAccountRepository.save(userAccount);

    // 🏡 Lấy địa chỉ từ LocationService
    Address address = locationService.getRandomAddress();
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
    
    
    private void seedConnections() {
        List<UserProfile> users = userProfileRepository.findAll().stream()
                .sorted(Comparator.comparing(UserProfile::getId))
                .skip(10) // Bỏ qua 10 user đầu tiên (admin)
                .limit(20) // Lấy tiếp 20 user (từ 10 đến 29)
                .collect(Collectors.toList());

        if (users.size() < 20) {
            throw new RuntimeException("Không đủ 20 user để tạo kết nối!");
        }

        List<UserProfile> senders = users.subList(0, 10);  // User 10 đến 19
        List<UserProfile> receivers = users.subList(10, 20); // User 20 đến 29

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

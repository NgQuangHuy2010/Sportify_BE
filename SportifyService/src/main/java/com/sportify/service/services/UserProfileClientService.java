package com.sportify.service.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sportify.service.dtos.UserProfileRequest;
import com.sportify.service.entities.Address;
import com.sportify.service.entities.Gender;
import com.sportify.service.entities.Sport;
import com.sportify.service.entities.UserAccount;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.enums.Role;
import com.sportify.service.repositories.AddressClientRepository;
import com.sportify.service.repositories.SportClientRepository;
import com.sportify.service.repositories.UserAccountRepository;
import com.sportify.service.repositories.UserProfileRepository;
import com.sportify.service.security.JwtService;
import com.sportify.service.security.dtos.AuthResponse;

import jakarta.transaction.Transactional;

@Service
public class UserProfileClientService {
	private static final String UPLOAD_DIR = "uploads/avatar/";
    @Autowired
    private UserProfileRepository userProfileRepository;
 
    @Autowired
    private SportClientRepository sportClientRepository;

    @Autowired
    private AddressClientRepository addressClientRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserAccountService userAccountService;
    @Transactional
    public void saveUserProfileWithSports(UserProfileRequest userProfileRequest, MultipartFile avatar) throws IOException {
    	  UserAccount userAccount = userAccountService.registerUser(
    	            userProfileRequest.getEmail(), 
    	            userProfileRequest.getPassword()
    	        );
    	  
        UserProfile userProfile = mapToUserProfile(userProfileRequest);
        userProfile.setUserAccount(userAccount);
        if (avatar != null && !avatar.isEmpty()) {
            String avatarFileName = saveImage(avatar);
            userProfile.setAvatar(avatarFileName);
        }
        UserProfile savedUserProfile = userProfileRepository.save(userProfile);
        userAccount.setUserProfile(savedUserProfile); 
        userAccountRepository.save(userAccount);
        
        if (userProfileRequest.getSports() != null && !userProfileRequest.getSports().isEmpty()) {
            List<Sport> sports = findValidSports(userProfileRequest.getSports());
            savedUserProfile.setSports(new ArrayList<>(sports));
        }
        
        if (userProfileRequest.getAddress() != null) {
            Address address = mapToAddress(userProfileRequest.getAddress());
            address.setUserProfile(savedUserProfile); 
            savedUserProfile.setAddress(address); 
            addressClientRepository.save(address);
        }
        userProfileRepository.save(savedUserProfile);  
    }
    
    private UserProfile mapToUserProfile(UserProfileRequest userProfileRequest) {
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstname(userProfileRequest.getFirstname());
        userProfile.setLastname(userProfileRequest.getLastname());
        userProfile.setEmail(userProfileRequest.getEmail());
        userProfile.setBirthday(userProfileRequest.getBirthday());
        userProfile.setPhone(userProfileRequest.getPhone());
        userProfile.setBio(userProfileRequest.getBio());
        userProfile.setGender(userProfileRequest.getGender());
        userProfile.setRole(Role.USER);
        return userProfile;
    }
    
    private Address mapToAddress(Address addressRequest) {
        Address address = new Address();
        address.setWard(addressRequest.getWard());
        address.setDistrict(addressRequest.getDistrict());
        address.setCity(addressRequest.getCity());
        address.setNo(addressRequest.getNo());
        return address;
    }


    private List<Sport> findValidSports(List<Long> sportIds) {
        return sportIds.stream()
                .map(sportId -> sportClientRepository.findById(sportId)
                        .orElseThrow(() -> new IllegalArgumentException("Sport ID không hợp lệ: " + sportId)))
                .collect(Collectors.toList());
    }
    
    private String saveImage(MultipartFile image) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path imagePath = Paths.get(UPLOAD_DIR, fileName);
        Files.createDirectories(imagePath.getParent()); // Tạo thư mục nếu chưa có
        Files.write(imagePath, image.getBytes());
        return fileName;
    }
}




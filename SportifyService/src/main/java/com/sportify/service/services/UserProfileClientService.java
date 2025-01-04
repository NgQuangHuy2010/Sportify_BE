package com.sportify.service.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.UserProfileRequest;
import com.sportify.service.entities.Address;
import com.sportify.service.entities.Gender;
import com.sportify.service.entities.Sport;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.repositories.AddressClientRepository;
import com.sportify.service.repositories.SportClientRepository;
import com.sportify.service.repositories.UserProfileRepository;


import jakarta.transaction.Transactional;

@Service
public class UserProfileClientService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private SportClientRepository sportClientRepository;

    @Autowired
    private AddressClientRepository addressClientRepository;
    
    @Transactional
    public void saveUserProfileWithSports(UserProfileRequest userProfileRequest) {

        UserProfile userProfile = mapToUserProfile(userProfileRequest);
        UserProfile savedUserProfile = userProfileRepository.save(userProfile);

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
        userProfile.setAvatar(userProfileRequest.getAvatar());
        userProfile.setBio(userProfileRequest.getBio());
        userProfile.setGender(userProfileRequest.getGender());
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
}




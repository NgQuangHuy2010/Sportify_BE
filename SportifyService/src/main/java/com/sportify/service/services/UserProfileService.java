package com.sportify.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.entities.UserProfile;
import com.sportify.service.repositories.UserProfileRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserProfileService {
	@Autowired
	private UserProfileRepository profileRepository;
	
	public List<UserProfile> getRecommendUsersToConnect(){
		return profileRepository.findAll();  // Vi du thoi, khong phai code that
	}

}

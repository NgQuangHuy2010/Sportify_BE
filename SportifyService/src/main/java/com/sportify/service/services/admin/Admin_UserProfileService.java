package com.sportify.service.services.admin;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.admin.AdminMapper;
import com.sportify.service.dtos.admin.user.UserDetailDTO;
import com.sportify.service.dtos.admin.user.UserListDTO;
import com.sportify.service.dtos.admin.user.UserUpdateDTO;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.repositories.admin.Admin_UserProfileRepository;

@Service
public class Admin_UserProfileService {

	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private Admin_UserProfileRepository userRepository;
	
	 public Page<UserListDTO> getAllUserProfiles(String keyword, Pageable pageable) {
	        Page<UserProfile> userProfiles;
	        
	        if (keyword != null && !keyword.isEmpty()) {
	            userProfiles = userRepository.searchUsersByName(keyword, pageable);
	        } else {
	            userProfiles = userRepository.findAllUsersWithRoleUser(pageable);
	        }
	        
	        return userProfiles.map(adminMapper::UserProfileToUserList);
	    }

	    public Page<UserListDTO> getAllUsersBySport(Long sportId, Pageable pageable) {
	        Page<UserProfile> userProfiles = userRepository.findUsersBySportId(sportId, pageable);
	        return userProfiles.map(adminMapper::UserProfileToUserList);
	    }
	    
	    public Long getTotalUsersBySport(Long sportId) {
	        return userRepository.findUsersBySportId(sportId, Pageable.unpaged()).getTotalElements();
	    }
	    
	//
	public boolean toggleLock(Long id) throws Exception {
	     UserProfile user = userRepository.findById(id)
	             .orElseThrow(() -> new Exception("User not found with ID: " + id));
	     if (user.getIsLocked()) {
	         user.setIsLocked(false);
	         userRepository.save(user);
	         return false;
	     } else {
	    	 user.setIsLocked(true);
	    	 userRepository.save(user);
	         return true;
	     }
	 }
	
	//
	public UserDetailDTO getUserById(Long id) {
		UserProfile user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));;
		return adminMapper.UserProfileToUserDetailDto(user);
	}
	
	//Find users by name (Firstname or Lastname)

    public Page<UserListDTO> searchUsersByName(String name, Pageable pageable) {
        Page<UserProfile> users = userRepository.findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(name, name, pageable);
        return users.map(adminMapper::UserProfileToUserList);
    }

	
	//Update User:
	public UserDetailDTO updateUser(Long id, UserUpdateDTO userDto) throws Exception {
		UserProfile user = userRepository.findById(id)
				.orElseThrow(() -> new Exception("User not found with ID: " + id));
		adminMapper.updateUserFromDto(user, userDto);
		userRepository.save(user);
		return adminMapper.UserProfileToUserDetailDto(user);
		
	}
	
	// Danh sach Locked User:
	public Page<UserListDTO> getLockedUsers(Pageable pageable) {
        Page<UserProfile> lockedUsers = userRepository.findLockedUsers(pageable);
        return lockedUsers.map(adminMapper::UserProfileToUserList);
    }
	
}

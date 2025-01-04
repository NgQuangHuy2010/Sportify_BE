package com.sportify.service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.UserProfileRequest;
import com.sportify.service.services.UserProfileClientService;

@RestController
@RequestMapping("/api/userprofiles")
public class UserProfileClientController {
	@Autowired
	private UserProfileClientService userProfileClientService;

	@PostMapping("/save")
	public ResponseEntity<String> saveUserProfile(@RequestBody UserProfileRequest userProfileRequest) {

		try {
			userProfileClientService.saveUserProfileWithSports(userProfileRequest);

			return ResponseEntity.ok("Thêm thành công!!");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}
}

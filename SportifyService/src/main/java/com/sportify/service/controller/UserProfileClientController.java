package com.sportify.service.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportify.service.dtos.ListUserDTO;
import com.sportify.service.dtos.UserProfileRequest;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.security.JwtService;
import com.sportify.service.services.UserProfileClientService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/userprofiles")
public class UserProfileClientController {
	@Autowired
	private UserProfileClientService userProfileClientService;
    @Autowired
    private JwtService jwtService;
	@PostMapping("/save")
	public ResponseEntity<String> saveUserProfile(@RequestPart(name = "userProfileRequest", required = true) String userProfileJson,
	        @RequestPart(value = "avatar", required = false) MultipartFile avatar) {

		try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        UserProfileRequest userProfileRequest = objectMapper.readValue(userProfileJson, UserProfileRequest.class);
			userProfileClientService.saveUserProfileWithSports(userProfileRequest, avatar);
			String token = jwtService.generateToken(userProfileRequest.getEmail());
	          return ResponseEntity.ok(token);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}
	
	@GetMapping("/get-all-user")
    public ResponseEntity<List<ListUserDTO>> getUsersByCity(@RequestHeader("Authorization") String token) {
        // Loại bỏ tiền tố "Bearer " trong token
        String jwt = token.replace("Bearer ", "");
        String email = jwtService.extractEmail(jwt);

        List<ListUserDTO> users = userProfileClientService.getUsersByCity(email);
        return ResponseEntity.ok(users);
    }
}

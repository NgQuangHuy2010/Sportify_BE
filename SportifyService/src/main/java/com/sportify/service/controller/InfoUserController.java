package com.sportify.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.InfoUserDTO;
import com.sportify.service.entities.UserAccount;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.repositories.UserAccountRepository;
import com.sportify.service.repositories.UserProfileRepository;
import com.sportify.service.security.JwtService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class InfoUserController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@GetMapping("/info-user")
	public ResponseEntity<?> getUserInfo(@RequestHeader(value ="Authorization", required = true) String token) {
		if (token.startsWith("Bearer ")) {
			token = token.substring(7);
		}

		String email = jwtService.extractUsername(token);
		UserProfile user = userProfileRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
		InfoUserDTO userDTO = new InfoUserDTO(user);
		return ResponseEntity.ok(userDTO);
	}
}

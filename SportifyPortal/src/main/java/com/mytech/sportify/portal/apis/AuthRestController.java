package com.mytech.sportify.portal.apis;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytech.sportify.apis.response.JWTResponse;
import com.mytech.sportify.apis.response.MessageResponse;
import com.mytech.sportify.portal.dtos.LoginDTO;
import com.mytech.sportify.portal.security.AppUserDetails;
import com.mytech.sportify.portal.security.jwt.JwtUtils;
import com.mytech.thebags.service.dtos.UserDTO;
import com.mytech.thebags.service.entities.User;
import com.mytech.thebags.service.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apis/v1")
public class AuthRestController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserService userService;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> autheticateUser(@Valid @RequestBody UserDTO userDto) {
		User dbUser = userService.getByEmail(userDto.getEmail());
		if (dbUser != null) {
			return ResponseEntity.ok(new MessageResponse("Error", "Email da ton tai"));
		}
		User saveUser = userService.save(userDto);
		if (saveUser == null) {
			return ResponseEntity.ok(new MessageResponse("Error", "co loi xay ra, khong the dang ky"));
		}
		return ResponseEntity.ok(new MessageResponse("Success", "User registered"));
	}

	@PostMapping("/signin")
	public ResponseEntity<JWTResponse> autheticateUser(@Valid @RequestBody LoginDTO loginDTO) {
		User dbUser = userService.getByEmail(loginDTO.getEmail());
		if (dbUser == null) {
			return ResponseEntity.ok(new JWTResponse(null, null, null, null, "bad crendetial"));
		}
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
//		AppUserDetails appUserDetails = (AppUserDetails) authentication.getAuthorities().stream().map(item -> item.getAuthority());
		AppUserDetails appUserDetails = (AppUserDetails) authentication.getPrincipal();
		List<String> role = appUserDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		String jwt = jwtUtils.generateJwtToken(authentication);

		return ResponseEntity.ok(new JWTResponse(jwt, dbUser.getFullName(), dbUser.getEmail(), role, "success"));
	}

}

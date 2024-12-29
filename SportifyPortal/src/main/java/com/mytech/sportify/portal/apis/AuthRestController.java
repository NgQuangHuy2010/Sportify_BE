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
import com.sportify.service.UserService;

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
	public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody UserDTO userDto) {
		if (userService.isEmailExisted(userDTO.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		UserAcount user = UserMapper.MAPPER.userDTOToUser(userDTO);
		userService.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}


}

package com.sportify.service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.InfoUserDTO;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.repositories.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class getAllUserService {

	@Autowired
    private final UserProfileRepository userRepository;

    public List<InfoUserDTO> getAllUsers() {
        List<UserProfile> users = userRepository.findAll();
        return users.stream()
                .map(InfoUserDTO::new)  // Chuyển từ UserProfile → InfoUserDTO
                .collect(Collectors.toList());
    }
}

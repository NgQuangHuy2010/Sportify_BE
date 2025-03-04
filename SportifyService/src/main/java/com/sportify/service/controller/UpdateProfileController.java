package com.sportify.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.UpdateProfileRequest;
import com.sportify.service.services.UpdateProfileService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/update-profile")
@RequiredArgsConstructor
public class UpdateProfileController {

	@Autowired
    private final UpdateProfileService updateProfileService;

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateProfile(
    		@PathVariable("userId") Long userId,  
            @RequestBody UpdateProfileRequest request) {

        updateProfileService.updateUserProfile(userId, request);
        return ResponseEntity.ok("Cập nhật thành công!");
    }
}

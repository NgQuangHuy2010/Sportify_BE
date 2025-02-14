package com.sportify.service.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.admin.user.UserDetailDTO;
import com.sportify.service.dtos.admin.user.UserListDTO;
import com.sportify.service.dtos.admin.user.UserUpdateDTO;
import com.sportify.service.services.admin.Admin_UserProfileService;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "http://localhost:3000")
public class Admin_UserProfileController {
	@Autowired
	private Admin_UserProfileService userService;
	

    @GetMapping
    public ResponseEntity<Page<UserListDTO>> getAllUsers(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<UserListDTO> users = userService.getAllUserProfiles(keyword, pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public UserDetailDTO getUserById(@PathVariable("id") Long id) {
    	return userService.getUserById(id);
    }
    

    @GetMapping("/by-sport/{sportId}")
    public ResponseEntity<Page<UserListDTO>> getAllUsersBySport(
            @PathVariable("sportId") Long sportId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<UserListDTO> users = userService.getAllUsersBySport(sportId, pageable);
        return ResponseEntity.ok(users);
    }
    

    @PatchMapping("/{id}/toggle-lock")
    public ResponseEntity<String> toggleLock(@PathVariable("id") Long id) throws Exception {
        boolean result = userService.toggleLock(id);
        if (result) {
            return ResponseEntity.ok("User has been locked successfully.");
        } else {
            return ResponseEntity.ok("User has been unlocked successfully.");
        }
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateDTO updateUserDTO) throws Exception {
        UserDetailDTO updatedUser = userService.updateUser(id, updateUserDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserListDTO>> searchUsersByName(
            @RequestParam("name") String name,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<UserListDTO> users = userService.searchUsersByName(name, pageable);
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/locked")
    public ResponseEntity<Page<UserListDTO>> getLockedUsers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<UserListDTO> lockedUsers = userService.getLockedUsers(pageable);
        return ResponseEntity.ok(lockedUsers);
    }

}

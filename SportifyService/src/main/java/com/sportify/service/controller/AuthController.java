package com.sportify.service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sportify.service.entities.Address;
import com.sportify.service.entities.Sport;
import com.sportify.service.entities.UserAccount;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.enums.Gender;
import com.sportify.service.enums.Role;
import com.sportify.service.repositories.AddressClientRepository;
import com.sportify.service.repositories.UserAccountRepository;
import com.sportify.service.repositories.UserProfileRepository;
import com.sportify.service.repositories.admin.Admin_SportRepository;
import com.sportify.service.security.JwtService;
import com.sportify.service.security.dtos.AuthResponse;
import com.sportify.service.security.dtos.LoginRequest;
import com.sportify.service.security.dtos.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserAccountRepository userAccountRepository;
    private final Admin_SportRepository sportRepository;
    private final AddressClientRepository addressRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword())
        );
        UserAccount user = userAccountRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        String token = jwtService.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {


        // Tạo UserProfile
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstname(request.getFirstname());
        userProfile.setLastname(request.getLastname());
        userProfile.setEmail(request.getEmail());
        userProfile.setPhone(request.getPhone());
        userProfile.setBio(request.getBio());
        userProfile.setGender(Gender.valueOf(request.getGender()));
        userProfile.setRole(Role.valueOf(request.getRole()));
//        userProfile.setAvatar(avatarPath);

        // Lấy danh sách Sport từ danh sách ID
        List<Sport> sports = sportRepository.findAllById(request.getSportIds());
        userProfile.setSports(sports);

        userProfileRepository.save(userProfile);

        // Tạo UserAccount
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(request.getUsername());
        userAccount.setEmail(request.getEmail());
        userAccount.setPassword(passwordEncoder.encode(request.getPassword()));
        userAccount.setRegistrationMethod(request.getRegistrationMethod());
        userAccount.setUserProfile(userProfile);
        userAccountRepository.save(userAccount);

        // Tạo Address
        Address address = new Address();
        address.setWard(request.getWard());
        address.setDistrict(request.getDistrict());
        address.setCity(request.getCity());
        address.setNo(request.getNo());
        address.setUserProfile(userProfile);
        addressRepository.save(address);

        return ResponseEntity.ok("User registered successfully!");
    }

 
}
//
//private String saveAvatar(MultipartFile avatarFile) {
//    // Xử lý lưu file ảnh đại diện vào thư mục và trả về đường dẫn
//    return "uploads/avatars/" + avatarFile.getOriginalFilename();
//}
//
//// Xử lý lưu avatar (nếu có)
//String avatarPath = null;
//if (avatarFile != null && !avatarFile.isEmpty()) {
//    avatarPath = saveAvatar(avatarFile);
//}
//,
//@RequestPart(value = "avatar", required = false) MultipartFile avatarFile


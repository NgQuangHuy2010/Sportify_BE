package com.sportify.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.sportify.service.dtos.RegisterRequest;
import com.sportify.service.entities.UserAccount;
import com.sportify.service.repositories.UserAccountRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        // Kiểm tra username đã tồn tại chưa
        if (userAccountRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        // Tạo tài khoản mới
        UserAccount user = new UserAccount();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Mã hóa mật khẩu
        user.setRegistrationMethod("NORMAL");

        // Lưu tài khoản vào cơ sở dữ liệu
        userAccountRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}

package com.sportify.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.services.UserAccountService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserAccountController {
    
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam("email") String email) {
        boolean exists = userAccountService.isEmailExists(email);
        return ResponseEntity.ok(exists);
    }
}


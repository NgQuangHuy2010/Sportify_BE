package com.sportify.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.sportify.service.dtos.InfoUserDTO;
import com.sportify.service.services.getAllUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class getAllUserController {

	@Autowired
    private final getAllUserService getAllUserService;

    @GetMapping("/get-all-user")
    public ResponseEntity<List<InfoUserDTO>> getAllUsers() {
        List<InfoUserDTO> users = getAllUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}


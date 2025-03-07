package com.sportify.service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.ConnectionRequestDTO;
import com.sportify.service.dtos.ListUserDTO;
import com.sportify.service.entities.ConnectionRequest;
import com.sportify.service.services.ConnectionRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/connections")
@RequiredArgsConstructor
public class ConnectionRequestController {

    private final ConnectionRequestService connectionRequestService;

    @PostMapping("/send/{receiverId}")
    public ResponseEntity<String> sendRequest(
            @RequestHeader("Authorization") String token,
            @PathVariable("receiverId") Long receiverId) {
        String response = connectionRequestService.sendConnectionRequest(token.replace("Bearer ", ""), receiverId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/accept/{requestId}")
    public ResponseEntity<String> acceptRequest(@PathVariable("requestId") Long requestId) {
        String response = connectionRequestService.acceptConnectionRequest(requestId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable("requestId") Long requestId) {
        String response = connectionRequestService.rejectConnectionRequest(requestId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<ConnectionRequestDTO>> getPendingRequests(@RequestHeader("Authorization") String token) {
        List<ConnectionRequestDTO> requests = connectionRequestService.getPendingRequests(token.replace("Bearer ", ""));
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/friends")
    public ResponseEntity<List<ListUserDTO>> getConnectedUsers(@RequestHeader("Authorization") String token) {
        List<ListUserDTO> users = connectionRequestService.getConnectedUsers(token.replace("Bearer ", ""));
        return ResponseEntity.ok(users);
    }
    
    @DeleteMapping("/cancel/{receiverId}")
    public ResponseEntity<?> cancelConnectionRequest(@PathVariable("receiverId") Long receiverId, @RequestHeader("Authorization") String token) {
        String response = connectionRequestService.cancelConnectionRequest(token.replace("Bearer ", ""), receiverId);
        return ResponseEntity.ok().body(response);
    }

    
}
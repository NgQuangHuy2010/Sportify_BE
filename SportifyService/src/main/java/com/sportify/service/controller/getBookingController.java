package com.sportify.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.BookingDto;
import com.sportify.service.dtos.booking.BookingDTO;
import com.sportify.service.entities.Booking;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.security.JwtService;
import com.sportify.service.services.BookingService;
import com.sportify.service.services.UserProfileClientService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class getBookingController {
	private final BookingService bookingService;
    private final JwtService jwtService;
    private final UserProfileClientService userProfileClientService;

    @GetMapping
    public ResponseEntity<List<BookingDto>> getUserBookings(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", "");
        String email = jwtService.extractEmail(jwt);
        UserProfile user = userProfileClientService.findUserByEmail(email);

        List<BookingDto> bookings = bookingService.getBookingsByUserId(user.getId());
        return ResponseEntity.ok(bookings);
    }
	
}

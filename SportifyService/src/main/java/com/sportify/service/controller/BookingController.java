package com.sportify.service.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.booking.BookedTimeSlotDTO;
import com.sportify.service.dtos.booking.BookingDTO;
import com.sportify.service.dtos.booking.CreateBookingDTO;
import com.sportify.service.services.BookingService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/booked-time-slots")
    public ResponseEntity<List<BookedTimeSlotDTO>> getBookedTimeSlots(
            @RequestParam("sportFieldId") Long sportFieldId,
            @RequestParam("bookingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingDate) {
        
        List<BookedTimeSlotDTO> bookedTimeSlots = bookingService.getBookedTimeSlots(sportFieldId, bookingDate);
        return ResponseEntity.ok(bookedTimeSlots);
    }
    

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody CreateBookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }
}

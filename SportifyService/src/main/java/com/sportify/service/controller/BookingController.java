package com.sportify.service.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getBookingInfo(
            @RequestParam(name = "sportFieldId") Long sportFieldId,
            @RequestParam(name = "timeSlotId") Long timeSlotId,
            @RequestParam(name = "bookingDate") String bookingDate) {

        LocalDate date = LocalDate.parse(bookingDate);
        Map<String, Object> response = bookingService.getBookingInfo(sportFieldId, timeSlotId, date);

        return ResponseEntity.ok(response);
    }
    
    
    @DeleteMapping("/{timeSlotId}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable("timeSlotId") Long timeSlotId, @RequestParam("userId") Long userId) {
        try {
            bookingService.deleteBookingByTimeSlotAndUser(timeSlotId, userId);
            return ResponseEntity.ok("Booking canceled successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    
}

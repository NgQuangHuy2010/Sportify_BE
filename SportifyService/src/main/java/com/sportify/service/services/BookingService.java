package com.sportify.service.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.booking.BookedTimeSlotDTO;
import com.sportify.service.dtos.booking.BookingDTO;
import com.sportify.service.dtos.booking.CreateBookingDTO;
import com.sportify.service.entities.Booking;
import com.sportify.service.entities.SportsField;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.enums.BookingStatus;
import com.sportify.service.repositories.BookingRepository;
import com.sportify.service.repositories.UserProfileRepository;
import com.sportify.service.repositories.admin.Admin_SportsFieldRepository;

@Service
public class BookingService {


    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private Admin_SportsFieldRepository sportFieldRepository;
    
    @Autowired
    private UserProfileRepository userProfileRepository;

    public List<BookedTimeSlotDTO> getBookedTimeSlots(Long sportFieldId, LocalDate bookingDate) {
        return bookingRepository.findBookedTimeSlots(sportFieldId, bookingDate);
    }
    

    public BookingDTO createBooking(CreateBookingDTO bookingDTO) {
        // Kiểm tra sân có tồn tại không
        SportsField sportField = sportFieldRepository.findById(bookingDTO.getSportFieldId())
                .orElseThrow(() -> new RuntimeException("Sport Field not found"));

        // Kiểm tra người dùng có tồn tại không
        UserProfile user = userProfileRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        

        // Chuyển String "09:00" thành LocalTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.parse(bookingDTO.getStartTime(), formatter);
        LocalTime endTime = LocalTime.parse(bookingDTO.getEndTime(), formatter);

        // Kiểm tra giờ có bị trùng không
        boolean isOverlapping = bookingRepository.existsOverlappingBooking(
                bookingDTO.getSportFieldId(),
                bookingDTO.getBookingDate(),
                startTime,
                endTime
        );

        if (isOverlapping) {
            throw new RuntimeException("The selected time slot is not available.");
        }

        // Nếu giờ trống, tiếp tục tạo booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSportsField(sportField);
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setStatus(BookingStatus.PENDING);
        booking.setNotes(bookingDTO.getNotes());

        booking = bookingRepository.save(booking);

        return new BookingDTO(booking);
    }
}

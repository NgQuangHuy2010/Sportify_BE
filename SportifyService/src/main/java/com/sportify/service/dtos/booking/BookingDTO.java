package com.sportify.service.dtos.booking;

import java.time.LocalDate;
import java.time.LocalTime;

import com.sportify.service.entities.Booking;
import com.sportify.service.enums.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long sportFieldId;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private BookingStatus status;
    private String notes;
    
    public BookingDTO(Booking booking) {
        this.id = booking.getId();
        this.userId = booking.getUser().getId();
        this.sportFieldId = booking.getSportsField().getId();
        this.bookingDate = booking.getBookingDate();
        this.startTime = booking.getStartTime();
        this.endTime = booking.getEndTime();
        this.status = booking.getStatus();
        this.notes = booking.getNotes();
    }
}

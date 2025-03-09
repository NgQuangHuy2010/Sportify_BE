package com.sportify.service.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.sportify.service.enums.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class BookingDto {
    private Long id;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String notes;
    private BookingStatus status;
    private Long userId;
    private Long sportsFieldId;
    private String sportsFieldName;
    private Long timeSlotId;
    private String timeSlotRange; // Sẽ chứa "08:00 - 09:00"
    private String sportCenterName;
    private String sportCenterAddress;
}


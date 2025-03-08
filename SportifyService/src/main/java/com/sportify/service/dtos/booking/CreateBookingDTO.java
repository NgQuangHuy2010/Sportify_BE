package com.sportify.service.dtos.booking;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingDTO {
	private Long userId;
    private Long sportFieldId;
    private LocalDate bookingDate;
    private String startTime;  // Định dạng HH:mm
    private String endTime;    // Định dạng HH:mm
    private String notes;
}

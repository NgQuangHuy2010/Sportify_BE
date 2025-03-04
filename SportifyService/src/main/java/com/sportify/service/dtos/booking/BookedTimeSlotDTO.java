package com.sportify.service.dtos.booking;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookedTimeSlotDTO {
    private LocalTime startTime;
    private LocalTime endTime;
}

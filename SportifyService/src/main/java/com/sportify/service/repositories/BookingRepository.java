package com.sportify.service.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportify.service.dtos.booking.BookedTimeSlotDTO;
import com.sportify.service.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT COUNT(b) > 0 FROM Booking b " +
           "WHERE b.sportsField.id = :sportsFieldId " +
           "AND b.bookingDate = :bookingDate " +
           "AND ((b.startTime < :endTime AND b.endTime > :startTime))")
    boolean existsOverlappingBooking(
            @Param("sportsFieldId") Long sportsFieldId,
            @Param("bookingDate") LocalDate bookingDate,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );
    
    @Query("SELECT new com.sportify.service.dtos.booking.BookedTimeSlotDTO(b.startTime, b.endTime) " +
            "FROM Booking b " +
            "WHERE b.sportsField.id = :sportsFieldId " +
            "AND b.bookingDate = :bookingDate")
     List<BookedTimeSlotDTO> findBookedTimeSlots(
             @Param("sportsFieldId") Long sportsFieldId,
             @Param("bookingDate") LocalDate bookingDate
     );
}

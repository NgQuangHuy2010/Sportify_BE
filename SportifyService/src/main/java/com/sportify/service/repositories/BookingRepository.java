package com.sportify.service.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    
    
    
    
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.sportsField.id = :sportFieldId AND b.timeSlotSport.id = :timeSlotId AND b.bookingDate = :bookingDate")
    int countBookings(@Param("sportFieldId") Long sportFieldId, 
                      @Param("timeSlotId") Long timeSlotId, 
                      @Param("bookingDate") LocalDate bookingDate);


    @Query("SELECT b.user.id FROM Booking b WHERE b.sportsField.id = :sportFieldId AND b.timeSlotSport.id = :timeSlotId AND b.bookingDate = :bookingDate")
    List<Long> getBookedUsers(@Param("sportFieldId") Long sportFieldId, @Param("timeSlotId") Long timeSlotId, @Param("bookingDate") LocalDate bookingDate);


    @Modifying
    @Query("UPDATE Booking b SET b.status = 'CANCELED' WHERE b.id = :bookingId")
    void updateBookingStatusToCanceled(@Param("bookingId") Long bookingId);

    @Query("SELECT b FROM Booking b WHERE b.timeSlotSport.id = :timeSlotId AND b.user.id = :userId")
    Optional<Booking> findByTimeSlotSportIdAndUserId(@Param("timeSlotId") Long timeSlotId, @Param("userId") Long userId);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.sportsField.id = :fieldId AND b.timeSlotSport.id = :slotId")
    Integer countBookedPlayers(@Param("fieldId") Long fieldId, @Param("slotId") Long slotId);
    
    
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.sportsField.id = :fieldId AND b.timeSlotSport.id = :slotId AND b.bookingDate = :date")
    int countBySportsFieldIdAndTimeSlotIdAndBookingDate(
        @Param("fieldId") Long sportsFieldId, 
        @Param("slotId") Long timeSlotId, 
        @Param("date") LocalDate bookingDate
    );

    
    
    
    
    
    
    
    
}

package com.sportify.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportify.service.entities.SportsField;
import com.sportify.service.entities.TimeSlotSport;

public interface TimeSlotSportRepository extends JpaRepository<TimeSlotSport, Long>{
	 List<TimeSlotSport> findBySportsFieldId(Long sportsFieldId);
	 List<TimeSlotSport> findBySportsField(SportsField sportsField);
	 @Query("SELECT t.maxPlayers FROM TimeSlotSport t WHERE t.id = :timeSlotId")
	    int findMaxPlayers(@Param("timeSlotId") Long timeSlotId);
	 
	 
}

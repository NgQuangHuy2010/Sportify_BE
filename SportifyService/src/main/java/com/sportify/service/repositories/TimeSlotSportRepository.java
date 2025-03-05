package com.sportify.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.service.entities.TimeSlotSport;

public interface TimeSlotSportRepository extends JpaRepository<TimeSlotSport, Long>{
	 List<TimeSlotSport> findBySportsFieldId(Long sportsFieldId);
}

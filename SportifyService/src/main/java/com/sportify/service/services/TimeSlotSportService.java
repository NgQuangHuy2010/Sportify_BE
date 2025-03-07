package com.sportify.service.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.TimeSlotSportDTO;
import com.sportify.service.dtos.admin.sport.SportsFieldDTO;
import com.sportify.service.dtos.admin.sport.UpdateSportsFieldDTO;
import com.sportify.service.entities.SportsCenter;
import com.sportify.service.entities.SportsField;
import com.sportify.service.entities.TimeSlotSport;
import com.sportify.service.repositories.TimeSlotSportRepository;
import com.sportify.service.repositories.admin.Admin_SportsFieldRepository;

@Service
public class TimeSlotSportService {
	@Autowired
	private TimeSlotSportRepository timeSlotRepository;

	@Autowired
	private Admin_SportsFieldRepository sportsFieldRepository;

	public TimeSlotSportDTO createTimeSlot( TimeSlotSportDTO dto) {
		
		  SportsField sportsField = sportsFieldRepository.findById(dto.getSportsFieldId())
	                .orElseThrow(() -> new RuntimeException("Sports field not found"));
		TimeSlotSport timeSlot = new TimeSlotSport();
		timeSlot.setSportsField(sportsField);
		timeSlot.setStartTime(dto.getStartTime());
		timeSlot.setEndTime(dto.getEndTime());
		timeSlot.setMaxPlayers(dto.getMaxPlayers());
		timeSlot.setSubPlayers(dto.getSubPlayers());
		timeSlot.setDate(Optional.ofNullable(dto.getDate()).orElse(LocalDate.now()));

		  TimeSlotSport savedSlot = timeSlotRepository.save(timeSlot);
		  return new TimeSlotSportDTO(
			        savedSlot.getId(),
			        savedSlot.getSportsField().getId(),
			        savedSlot.getStartTime().toString(),
			        savedSlot.getEndTime().toString(),
			        savedSlot.getMaxPlayers(),
			        savedSlot.getSubPlayers(),
			        savedSlot.getDate()
			    );
	}

	public List<TimeSlotSportDTO> getTimeSlotsByField(Long sportsFieldId) {
	    return timeSlotRepository.findBySportsFieldId(sportsFieldId)
	        .stream()
	        .map(timeSlot -> new TimeSlotSportDTO(timeSlot))
	        .collect(Collectors.toList());
	}



	
}

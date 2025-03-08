package com.sportify.service.dtos;

import java.sql.Date;
import java.time.LocalDate;

import com.sportify.service.entities.SportsField;
import com.sportify.service.entities.TimeSlotSport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlotSportDTO {
	
	private Long id;
	    private Long sportsFieldId;
	    private String startTime;
	    private String endTime;
	    private Integer maxPlayers;
	    private Integer subPlayers;
	    private LocalDate date;
	    public TimeSlotSportDTO(TimeSlotSport timeSlot) {
	        this.id = timeSlot.getId();
	        this.sportsFieldId = timeSlot.getSportsField().getId();
	        this.startTime = timeSlot.getStartTime();
	        this.endTime = timeSlot.getEndTime();
	        this.maxPlayers = timeSlot.getMaxPlayers();
	        this.subPlayers = timeSlot.getSubPlayers();
	        this.date = timeSlot.getDate();
	    }

}

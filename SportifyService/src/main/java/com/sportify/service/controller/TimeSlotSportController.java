package com.sportify.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.TimeSlotSportDTO;
import com.sportify.service.services.TimeSlotSportService;

@RestController
@RequestMapping("/api/time-slots")
public class TimeSlotSportController {

	  @Autowired
	    private TimeSlotSportService timeSlotService;
	  
	  @PostMapping
	  public ResponseEntity<TimeSlotSportDTO> createTimeSlot(@RequestBody TimeSlotSportDTO dto) {
	      TimeSlotSportDTO timeSlotSport = timeSlotService.createTimeSlot(dto);
	      return ResponseEntity.status(HttpStatus.CREATED).body(timeSlotSport);
	  }


	  @GetMapping("/{sportsFieldId}")
	  public ResponseEntity<List<TimeSlotSportDTO>> getTimeSlot(@PathVariable("sportsFieldId") Long sportsFieldId) {
	      return ResponseEntity.ok(timeSlotService.getTimeSlotsByField(sportsFieldId));
	  } 

}

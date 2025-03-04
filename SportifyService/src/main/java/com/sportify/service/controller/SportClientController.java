package com.sportify.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.entities.Sport;
import com.sportify.service.services.SportClientService;

@RestController
@RequestMapping("/api/sports")
@CrossOrigin(origins = "http://localhost:3000")
public class SportClientController {
	@Autowired
    private SportClientService sportService;

	 @PostMapping("/save")
	    public ResponseEntity<?> saveSports(@RequestBody List<Sport> sports) {
		 System.out.println("Controller method saveSports is called!");
	        if (sports == null || sports.isEmpty()) {
	            return ResponseEntity.badRequest().body("Invalid request body: sports list is empty");
	        }

	        try {
	            List<Sport> savedSports = sportService.saveSports(sports);
	            return ResponseEntity.ok(savedSports);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving sports: " + e.getMessage());
	        }
	    }
}

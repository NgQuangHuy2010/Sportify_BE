package com.sportify.service.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.admin.sport.SportsCenterDTO;
import com.sportify.service.services.admin.Admin_SportsCenterService;

@RestController
@RequestMapping("/api/admin/sports-centers")
@CrossOrigin(origins = "http://localhost:3000")
public class Admin_SportsCenterController {
	 @Autowired
	    private Admin_SportsCenterService sportsCenterService;

	    @GetMapping
	    public ResponseEntity<List<SportsCenterDTO>> getAllSportsCenters() {
	        return ResponseEntity.ok(sportsCenterService.getAllSportsCenters());
	    }

	    @PostMapping
	    public ResponseEntity<SportsCenterDTO> createSportsCenter(@RequestBody SportsCenterDTO dto) {
	        return ResponseEntity.status(HttpStatus.CREATED).body(sportsCenterService.createSportsCenter(dto));
	    }
}

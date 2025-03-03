package com.sportify.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.AddressClientDTO;

import com.sportify.service.services.AddressClientService;

@RestController
@RequestMapping("/api/update-address-client")
@CrossOrigin(origins = "http://localhost:3000")
public class AddressClientController {
	
	 private final AddressClientService addressClientService;

	    public AddressClientController(AddressClientService addressClientService) {
	        this.addressClientService = addressClientService;
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<AddressClientDTO> updateAddressClient(@PathVariable("id") Long id, @RequestBody AddressClientDTO dto) {
	        AddressClientDTO updatedAddress = addressClientService.updateAddressClient(id, dto);
	        return ResponseEntity.ok(updatedAddress);
	    }
}

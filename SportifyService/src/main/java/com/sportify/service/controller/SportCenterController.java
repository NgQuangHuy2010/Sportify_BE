package com.sportify.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.admin.sport.SportsCenterDTO;
import com.sportify.service.services.SportsCenterService;

//
//@RestController
//@RequestMapping("/api/sportsCenters")
////public class SportCenterController {
////    @Autowired
////    private SportsCenterService sportsCenterService;
////
////    @GetMapping("/availability")
////    public ResponseEntity<List<SportsCenterDTO>> getAvailableSportsCenters() {
////        List<SportsCenterDTO> availableCenters = sportsCenterService.get();
////        return ResponseEntity.ok(availableCenters);
////    }
////}


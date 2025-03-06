package com.sportify.service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.admin.AdminMapper;
import com.sportify.service.dtos.admin.sport.SportsCenterDTO;
import com.sportify.service.entities.SportsCenter;
import com.sportify.service.entities.SportsField;
import com.sportify.service.entities.TimeSlotSport;
import com.sportify.service.repositories.BookingRepository;
import com.sportify.service.repositories.TimeSlotSportRepository;
import com.sportify.service.repositories.admin.Admin_SportsCenterRepository;

@Service
public class SportsCenterService {
    @Autowired
    private Admin_SportsCenterRepository sportsCenterRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TimeSlotSportRepository timeSlotSportRepository;

    
  
    
    
    
}

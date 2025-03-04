package com.sportify.service.services.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.admin.AdminMapper;
import com.sportify.service.dtos.admin.sport.SportsCenterDTO;
import com.sportify.service.entities.SportsCenter;
import com.sportify.service.repositories.admin.Admin_SportsCenterRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class Admin_SportsCenterService {
	@Autowired
    private Admin_SportsCenterRepository sportsCenterRepository;

    @Autowired
    private AdminMapper mapper;

    public List<SportsCenterDTO> getAllSportsCenters() {
        return sportsCenterRepository.findAll().stream()
                .map(mapper::SportsCenterToDTO)
                .collect(Collectors.toList());
    }

    public SportsCenterDTO createSportsCenter(SportsCenterDTO dto) {
        SportsCenter sportsCenter = mapper.SportsCemterDtoToEntity(dto);
        sportsCenterRepository.save(sportsCenter);
        return mapper.SportsCenterToDTO(sportsCenter);
    }
}

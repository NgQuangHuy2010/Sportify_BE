package com.sportify.service.services.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.admin.AdminMapper;
import com.sportify.service.dtos.admin.sport.CreateSportsFieldDTO;
import com.sportify.service.dtos.admin.sport.SportsFieldDTO;
import com.sportify.service.dtos.admin.sport.UpdateSportsFieldDTO;
import com.sportify.service.entities.SportsCenter;
import com.sportify.service.entities.SportsField;
import com.sportify.service.repositories.admin.Admin_SportsCenterRepository;
import com.sportify.service.repositories.admin.Admin_SportsFieldRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class Admin_SportsFieldService {

    @Autowired
    private Admin_SportsFieldRepository sportsFieldRepository;

    @Autowired
    private Admin_SportsCenterRepository sportsCenterRepository;

    @Autowired
    private AdminMapper mapper;

    public List<SportsFieldDTO> getFieldsByCenterId(Long sportsCenterId) {
        return sportsFieldRepository.findBySportsCenterId(sportsCenterId).stream()
                .map(mapper::SportsFieldToDTO)
                .collect(Collectors.toList());
    }

    public SportsFieldDTO createSportsField(CreateSportsFieldDTO dto) {
        SportsCenter sportsCenter = sportsCenterRepository.findById(dto.getSportsCenterId())
                .orElseThrow(() -> new RuntimeException("Sports center not found"));

        SportsField sportsField = mapper.SportsFieldDtoToEntity(dto, sportsCenter);
        sportsFieldRepository.save(sportsField);
        return mapper.SportsFieldToDTO(sportsField);
    }

    public SportsFieldDTO updateSportsField(Long id, UpdateSportsFieldDTO dto) {
        SportsField sportsField = sportsFieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sports field not found"));

        mapper.updateSportFieldEntity(sportsField, dto);
        sportsFieldRepository.save(sportsField);
        return mapper.SportsFieldToDTO(sportsField);
    }
}


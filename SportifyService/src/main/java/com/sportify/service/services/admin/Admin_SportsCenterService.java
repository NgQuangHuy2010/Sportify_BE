package com.sportify.service.services.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.admin.AdminMapper;
import com.sportify.service.dtos.admin.sport.SportsCenterDTO;
import com.sportify.service.entities.Booking;
import com.sportify.service.entities.SportsCenter;
import com.sportify.service.entities.TimeSlotSport;
import com.sportify.service.repositories.BookingRepository;
import com.sportify.service.repositories.TimeSlotSportRepository;
import com.sportify.service.repositories.admin.Admin_SportsCenterRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class Admin_SportsCenterService {
	@Autowired
    private Admin_SportsCenterRepository sportsCenterRepository;

    @Autowired
    private AdminMapper mapper;
    @Autowired
    private TimeSlotSportRepository timeSlotSportRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public List<Map<String, Object>> getAllSportsCenters() {
        return sportsCenterRepository.findAll().stream()
            .map(center -> {
                // Tạo object chứa dữ liệu của SportsCenter
                Map<String, Object> result = new HashMap<>();
                result.put("id", center.getId());
                result.put("name", center.getName());
                result.put("location", center.getLocation());
                result.put("description", center.getDescription());
                result.put("image", center.getImage());
                result.put("open_door", center.getOpen_door());

                // Kiểm tra xem có khung giờ nào còn chỗ trống không
                boolean hasAvailableSlots = center.getSportsFields().stream()
                    .anyMatch(field -> {
                        List<TimeSlotSport> slots = timeSlotSportRepository.findBySportsField(field);
                        return slots.stream().anyMatch(slot -> {
                            int bookedPlayers = bookingRepository.countBySportsFieldIdAndTimeSlotIdAndBookingDate(
                                    field.getId(), slot.getId(), slot.getDate()
                            );
                            int availableSlots = slot.getMaxPlayers() - bookedPlayers;
                            return availableSlots > 0; // Nếu còn chỗ trống thì true
                        });
                    });

                // Thêm thông tin `hasAvailableSlots` vào map
                result.put("hasAvailableSlots", hasAvailableSlots);
                return result;
            })
            .collect(Collectors.toList());
    }


    public SportsCenterDTO createSportsCenter(SportsCenterDTO dto) {
        SportsCenter sportsCenter = mapper.SportsCemterDtoToEntity(dto);
        sportsCenterRepository.save(sportsCenter);
        return mapper.SportsCenterToDTO(sportsCenter);
    }
}

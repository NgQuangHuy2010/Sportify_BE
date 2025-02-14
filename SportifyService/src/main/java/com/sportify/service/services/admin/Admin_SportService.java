package com.sportify.service.services.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.admin.AdminMapper;
import com.sportify.service.dtos.admin.sport.SportDetail;
import com.sportify.service.dtos.admin.sport.SportList;
import com.sportify.service.entities.Sport;
import com.sportify.service.repositories.admin.Admin_SportRepository;
import com.sportify.service.repositories.admin.Admin_UserProfileRepository;

@Service
public class Admin_SportService {
	
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
    private Admin_SportRepository sportRepository;
	@Autowired
	private Admin_UserProfileService userService;

    public List<SportList> getAllSports() {
        List<Sport> sports = sportRepository.findAll();
        return sports.stream()
                     .map(sport -> adminMapper.SportToSportList(sport)) // Chuyển đổi mỗi Sport thành SportDTO
                     .collect(Collectors.toList());
    }
    

    public SportDetail getSportById(Long id) {
    	System.out.println("ID: "+ id);
        Sport sport = sportRepository.findById(id)
                                     .orElseThrow(() -> new RuntimeException("Sport not found with id: " + id));
        if (sport != null) {

            String sportName = sport.getSportName();
            System.out.println("SPORT NAME: " + sport.getSportName());
        } else {
        	System.out.println("NO SPORT");
        }
//        int userCount = sport.getUserProfiles().size();
        Long userCount = userService.getTotalUsersBySport(id);
        System.out.println("USER COUNT: " + userCount);
        
        return adminMapper.SportToSportDetail(sport, userCount);
    }
    
    
}

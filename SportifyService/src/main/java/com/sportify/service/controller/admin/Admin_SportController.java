package com.sportify.service.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.admin.sport.SportDetail;
import com.sportify.service.dtos.admin.sport.SportList;
import com.sportify.service.services.admin.Admin_SportService;

@RestController
@RequestMapping("/api/admin/sports")
@CrossOrigin(origins = "http://localhost:3000")
public class Admin_SportController {
	@Autowired
	private Admin_SportService sportService;
	
    @GetMapping
    public List<SportList> getAllSports() {
        return sportService.getAllSports();
    }
    

    @GetMapping("/{id}")
    public SportDetail getSportById(@PathVariable("id") Long id) {
        return sportService.getSportById(id);
    }
	

}

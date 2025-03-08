

package com.sportify.service.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sportify.service.dtos.admin.sport.CreateSportDTO;
import com.sportify.service.dtos.admin.sport.SportDetail;
import com.sportify.service.dtos.admin.sport.SportList;
import com.sportify.service.entities.Sport;
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
    

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Sport> createSport(
            @RequestPart("sportName") String sportName,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        CreateSportDTO dto = new CreateSportDTO();
        dto.setSportName(sportName);
        dto.setImage(image);

        Sport createdSport = sportService.createSport(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSport);
    }
	

}

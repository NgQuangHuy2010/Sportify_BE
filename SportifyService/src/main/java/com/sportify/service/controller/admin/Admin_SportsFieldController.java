package com.sportify.service.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.admin.sport.CreateSportsFieldDTO;
import com.sportify.service.dtos.admin.sport.SportsFieldDTO;
import com.sportify.service.dtos.admin.sport.UpdateSportsFieldDTO;
import com.sportify.service.services.admin.Admin_SportsFieldService;

@RestController
@RequestMapping("/api/admin/sports-fields")
@CrossOrigin(origins = "http://localhost:3000")
public class Admin_SportsFieldController {

    @Autowired
    private Admin_SportsFieldService sportsFieldService;

    @GetMapping("/center/{sportsCenterId}")
    public ResponseEntity<List<SportsFieldDTO>> getFieldsByCenter(@PathVariable("sportsCenterId") Long sportsCenterId) {
        return ResponseEntity.ok(sportsFieldService.getFieldsByCenterId(sportsCenterId));
    }

    @PostMapping
    public ResponseEntity<SportsFieldDTO> createSportsField(@RequestBody CreateSportsFieldDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sportsFieldService.createSportsField(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SportsFieldDTO> updateSportsField(@PathVariable("id") Long id, @RequestBody UpdateSportsFieldDTO dto) {
        return ResponseEntity.ok(sportsFieldService.updateSportsField(id, dto));
    }

}

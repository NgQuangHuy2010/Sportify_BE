package com.sportify.service.controller.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.service.dtos.admin.user.UserStatisticsDTO;
import com.sportify.service.services.admin.AdminDashboardService;

@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminDashboardController {

    @Autowired
    private AdminDashboardService dashboardService;

    @GetMapping("/user-statistics")
    public ResponseEntity<UserStatisticsDTO> getUserStatistics() {
        return ResponseEntity.ok(dashboardService.getUserStatistics());
    }
    
    @GetMapping("/statistics/users-by-month")
    public ResponseEntity<Map<Integer, Long>> getUserStatisticsByMonth(
            @RequestParam(name = "year", required = false, defaultValue = "2024") int year) {
        
        Map<Integer, Long> statistics = dashboardService.getUserStatisticsByMonth(year);
        return ResponseEntity.ok(statistics);
    }

}

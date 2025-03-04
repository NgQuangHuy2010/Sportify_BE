package com.sportify.service.services.admin;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.admin.user.SportUserCountDTO;
import com.sportify.service.dtos.admin.user.UserStatisticsDTO;
import com.sportify.service.repositories.admin.Admin_UserProfileRepository;

@Service
public class AdminDashboardService {

    @Autowired
    private Admin_UserProfileRepository userRepository;

    public UserStatisticsDTO getUserStatistics() {
        long totalUsers = userRepository.countTotalUsers();
        long lockedUsers = userRepository.countLockedUsers();
        long newUsersThisMonth = userRepository.countNewUsersThisMonth();

        List<SportUserCountDTO> usersBySport = userRepository.countUsersBySport().stream()
                .map(result -> new SportUserCountDTO((String) result[0], (Long) result[1]))
                .collect(Collectors.toList());

        return new UserStatisticsDTO(totalUsers, lockedUsers, newUsersThisMonth, usersBySport);
    }
    
    public Map<Integer, Long> getUserStatisticsByMonth(int year) {
        List<Object[]> results = userRepository.countUsersByMonth(year);
        Map<Integer, Long> statistics = new LinkedHashMap<>();

        // Khởi tạo mặc định 12 tháng với giá trị 0
        for (int month = 1; month <= 12; month++) {
            statistics.put(month, 0L);
        }

        // Cập nhật các tháng có dữ liệu từ DB
        for (Object[] row : results) {
            Integer month = (Integer) row[0];   // Tháng (1 - 12)
            Long count = (Long) row[1];         // Số lượng user trong tháng
            statistics.put(month, count);
        }

        return statistics;
    }
}

package com.sportify.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.dtos.UpdateProfileRequest;
import com.sportify.service.entities.ConnectSetting;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.repositories.ConnectSettingRepository;
import com.sportify.service.repositories.UserProfileRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateProfileService {

	@Autowired
    private final UserProfileRepository userRepository;
	@Autowired
    private final ConnectSettingRepository connectSettingRepository;

    @Transactional
    public void updateUserProfile(Long userId, UpdateProfileRequest request) {
        UserProfile user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user!"));

        user.setFirstname(request.getFirstName());
        user.setLastname(request.getLastName());
        user.setGender(request.getGender());
        user.setBirthday(request.getBirthday());

        userRepository.save(user);  

        // ✅ Cập nhật ConnectSetting table (nếu có)
        if (request.getConnectSetting() != null) {
            ConnectSetting connectSetting = connectSettingRepository
                    .findByUserProfile(user)
                    .orElse(new ConnectSetting()); // Nếu chưa có, tạo mới

            connectSetting.setStatus(request.getConnectSetting().getStatus());
            connectSetting.setGenderFind(request.getConnectSetting().getGenderFind());
            connectSetting.setAgeMin(request.getConnectSetting().getAgeMin());
            connectSetting.setAgeMax(request.getConnectSetting().getAgeMax());

            connectSettingRepository.save(connectSetting);  // Lưu vào DB
        }
    }
}


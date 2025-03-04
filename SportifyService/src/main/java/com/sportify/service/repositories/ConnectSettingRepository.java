package com.sportify.service.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.service.entities.ConnectSetting;
import com.sportify.service.entities.UserProfile;
import java.util.List;



public interface ConnectSettingRepository extends JpaRepository<ConnectSetting, Long> {
	Optional<ConnectSetting>  findByUserProfile(UserProfile userProfile);
}

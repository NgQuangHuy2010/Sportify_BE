package com.sportify.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.service.entities.ConnectSetting;


public interface ConnectSettingRepository extends JpaRepository<ConnectSetting, Long> {

}

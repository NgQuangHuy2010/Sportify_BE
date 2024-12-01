package com.sportify.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.service.entities.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

}

package com.sportify.service.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.service.entities.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	 Optional<UserProfile> findById(Long id);
}

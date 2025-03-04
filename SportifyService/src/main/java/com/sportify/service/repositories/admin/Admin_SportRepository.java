package com.sportify.service.repositories.admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportify.service.entities.Sport;

public interface Admin_SportRepository  extends JpaRepository<Sport, Long> {
	
	@Query("SELECT s FROM Sport s JOIN FETCH s.userProfiles WHERE s.id = :id")
	Optional<Sport> findSportWithUserProfilesById(@Param("id") Long id);

}

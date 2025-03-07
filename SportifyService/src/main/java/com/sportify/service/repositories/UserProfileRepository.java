package com.sportify.service.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportify.service.entities.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	 Optional<UserProfile> findById(Long id);
	 Optional<UserProfile> findByEmail(String email);
	 
	 @Query("SELECT u FROM UserProfile u WHERE u.address.city = :city AND u.id <> :userId")
	    List<UserProfile> findUsersByCity(@Param("city") String city, @Param("userId") Long userId);
	 
	 @Query("""
			    SELECT u FROM UserProfile u 
			    WHERE u.address.city = :city 
			    AND u.id <> :userId 
			  AND u.id NOT IN (
			        SELECT cr.sender.id FROM ConnectionRequest cr 
			        WHERE cr.receiver.id = :userId AND cr.status IN ('PENDING', 'ACCEPTED')
			    ) 
			    AND u.id NOT IN (
			        SELECT cr.receiver.id FROM ConnectionRequest cr 
			        WHERE cr.sender.id = :userId AND cr.status IN ('PENDING', 'ACCEPTED')
			    )
			""")
			List<UserProfile> findUnconnectedUsersByCity(@Param("city") String city, @Param("userId") Long userId);
	 
}

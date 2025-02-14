package com.sportify.service.repositories.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportify.service.entities.SportsCenter;

@Repository
public interface Admin_SportsCenterRepository extends JpaRepository<SportsCenter, Long> {
}

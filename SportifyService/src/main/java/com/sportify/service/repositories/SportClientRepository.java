package com.sportify.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportify.service.entities.Sport;

public interface SportClientRepository extends JpaRepository<Sport, Long>{

}

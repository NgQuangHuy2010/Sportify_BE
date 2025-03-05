package com.sportify.service.repositories.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportify.service.entities.SportsField;

@Repository
public interface Admin_SportsFieldRepository extends JpaRepository<SportsField, Long> {
    List<SportsField> findBySportsCenterId(Long sportsCenterId);
    Optional<SportsField> findById(Long id);
}


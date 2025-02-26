package com.sportify.service.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportify.service.entities.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{
	Optional<UserAccount> findByUsername(String username);
    Boolean existsByUsername(String username);
    Optional<UserAccount> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<UserAccount> findByUsernameOrEmail(String username, String email);
}

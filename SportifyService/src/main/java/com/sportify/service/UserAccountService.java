package com.sportify.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.entities.UserAccount;
import com.sportify.service.repositories.UserAccountRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserAccountService {
	 @Autowired
	    private UserAccountRepository userAccountRepository;

	    public Optional<UserAccount> findByUsername(String username) {
	        return userAccountRepository.findByUsername(username);
	    }

	    public boolean existsByUsername(String username) {
	        return userAccountRepository.existsByUsername(username);
	    }

	    public UserAccount save(UserAccount user) {
	        return userAccountRepository.save(user);
	    }
}

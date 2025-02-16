package com.sportify.service.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sportify.service.entities.UserAccount;
import com.sportify.service.repositories.UserAccountRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserAccountService {
	 @Autowired
	    private UserAccountRepository userAccountRepository;
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	 
	 public UserAccount registerUser(String email, String password) {
	        if (userAccountRepository.existsByEmail(email)) {
	            throw new RuntimeException("Email đã tồn tại!");
	        }

	        UserAccount userAccount = new UserAccount();
	        userAccount.setEmail(email);
	        userAccount.setPassword(passwordEncoder.encode(password)); // Mã hóa mật khẩu
	        userAccountRepository.save(userAccount);
	        return userAccount;
	    }
	 public boolean isEmailExists(String email) {
	        return userAccountRepository.existsByEmail(email);
	    }
//	    public Optional<UserAccount> findByUsername(String username) {
//	        return userAccountRepository.findByUsername(username);
//	    }
//
//	    public boolean existsByUsername(String username) {
//	        return userAccountRepository.existsByUsername(username);
//	    }
//
//	    public UserAccount save(UserAccount user) {
//	        return userAccountRepository.save(user);
//	    }
}

package com.sportify.service.security;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sportify.service.entities.UserAccount;
import com.sportify.service.repositories.UserAccountRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findByUsernameOrEmail(email, email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
     // Chuyển đổi Role từ String thành GrantedAuthority
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getUserProfile().getRole().name()));

        return User.builder()
                .username(user.getEmail()) // Dùng email làm username
                .password(user.getPassword())
                .authorities(authorities) // Gán role làm authority
                .build();
    }
    
}
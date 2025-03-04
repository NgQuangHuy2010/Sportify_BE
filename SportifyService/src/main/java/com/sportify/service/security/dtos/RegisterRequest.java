package com.sportify.service.security.dtos;
import java.util.List;

import lombok.Data;


@Data
public class RegisterRequest {
    // Thông tin UserProfile
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String bio;
    private String gender;
    private String role;
    
    // Thông tin UserAccount
    private String username;
    private String password;
    private String registrationMethod;
    
    // Thông tin Address
    private String ward;
    private String district;
    private String city;
    private String no;
    
    // Danh sách Sports yêu thích
    private List<Long> sportIds;
}


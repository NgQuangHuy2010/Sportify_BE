package com.sportify.service.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.sportify.service.dtos.admin.sport.SportList;
import com.sportify.service.entities.UserProfile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListUserDTO {
	private Long userId;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String bio;
    private String gender;
    private String role;
    private String avatar;
    private String birthday;
    // Thông tin địa chỉ
    private String no;
    private String ward;
    private String district;
    private String city;

    // Danh sách môn thể thao
    private List<SportDTO> sports;

    public ListUserDTO(UserProfile userProfile) {
    	this.userId = userProfile.getId();
        this.firstname = userProfile.getFirstname();
        this.lastname = userProfile.getLastname();
        this.email = userProfile.getEmail();
        this.phone = userProfile.getPhone();
        this.bio = userProfile.getBio();
        this.gender = userProfile.getGender().name();
        this.role = userProfile.getRole().name();
        this.avatar = userProfile.getAvatar();
        this.birthday = userProfile.getBirthday().toString();
        if (userProfile.getAddress() != null) {
            this.no = userProfile.getAddress().getNo();
            this.ward = userProfile.getAddress().getWard();
            this.district = userProfile.getAddress().getDistrict();
            this.city = userProfile.getAddress().getCity();
        }
        

        // Chuyển đổi danh sách môn thể thao
        if (userProfile.getSports() != null) {
            this.sports = userProfile.getSports().stream()
                .map(sport -> new SportDTO(sport.getId(), sport.getSportName(), sport.getImage()))
                .collect(Collectors.toList());
        } else {
            this.sports = List.of(); // Tránh NullPointerException
        }
    }
}
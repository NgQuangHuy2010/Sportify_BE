package com.sportify.service.dtos;

import java.sql.Date;

import com.sportify.service.entities.ConnectSetting;
import com.sportify.service.enums.Gender;

import lombok.Data;

@Data
public class UpdateProfileRequest {
	 private String firstName;
	    private String lastName;
	    private String email;
	    private Gender gender;
	    private Date birthday;  // Ngày sinh dạng String hoặc LocalDate

	    private ConnectSetting connectSetting;  // Thông tin kết nối
}

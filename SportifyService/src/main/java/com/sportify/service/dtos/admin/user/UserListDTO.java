package com.sportify.service.dtos.admin.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListDTO {
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private Date birthday; 
	private String phone;
	private String avatar;
	private String bio;
	private String username;
	private boolean isLocked;
}

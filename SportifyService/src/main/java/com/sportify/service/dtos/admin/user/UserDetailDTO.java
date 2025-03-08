package com.sportify.service.dtos.admin.user;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.sportify.service.dtos.AddressClientDTO;
import com.sportify.service.dtos.admin.ConnectSettingDTO;
import com.sportify.service.dtos.admin.sport.SportList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDTO {
	private Long id;
	private LocalDateTime createdOn;
	private String firstName;
	private String lastname;
	private String email;
	private Date birthday; 
	private String phone;
	private String avatar;
	private String bio;
	private boolean isLocked;
	private UserAccountDTO userAccount;
	private ConnectSettingDTO connectSetting;
	private AddressClientDTO address;
	private List<SportList> sports;

}

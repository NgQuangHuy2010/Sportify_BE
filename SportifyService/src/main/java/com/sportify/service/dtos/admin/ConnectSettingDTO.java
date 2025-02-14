package com.sportify.service.dtos.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectSettingDTO {

	private String weekDay;
	private Integer fromTime;
	private Integer toTime;
	private Integer ageMax;
	private Integer ageMin;
	private String genderFind;
	private Integer status;

}

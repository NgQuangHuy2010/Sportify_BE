package com.sportify.service.dtos.admin.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatisticsDTO {
	 private long totalUsers;
	    private long lockedUsers;
	    private long newUsersThisMonth;
	    private List<SportUserCountDTO> usersBySport;


}

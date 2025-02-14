package com.sportify.service.dtos.admin.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportUserCountDTO {
	private String sportName;
    private long userCount;


}

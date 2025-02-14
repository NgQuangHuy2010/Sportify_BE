package com.sportify.service.dtos.admin.sport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportList {
    private Long id;
	private String sportName;
	private String image;
	
}

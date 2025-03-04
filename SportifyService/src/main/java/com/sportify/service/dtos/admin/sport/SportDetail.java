package com.sportify.service.dtos.admin.sport;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportDetail {
    private Long id;
	private String sportName;
	private String image;
	private Long usersCount;
	private LocalDateTime createdOn;

}

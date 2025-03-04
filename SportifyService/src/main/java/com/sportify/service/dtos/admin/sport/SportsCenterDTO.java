package com.sportify.service.dtos.admin.sport;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportsCenterDTO {
	private Long id;
	private String name;
	private String location;
	private String description;
	private String image;
	 private String open_door;
}

package com.sportify.service.dtos;

import com.sportify.service.dtos.admin.sport.SportList;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SportDTO {
	private Long id;
	private String sportName;
	private String imageUrl;
	
	public SportDTO(Long id, String sportName, String image) {
        this.id = id;
        this.sportName = sportName;
        this.imageUrl = image; // Đường dẫn đầy đủ
    }
	public SportDTO(String sportName, String image) {
        this.sportName = sportName;
        this.imageUrl = image; // Đường dẫn đầy đủ
    }
}

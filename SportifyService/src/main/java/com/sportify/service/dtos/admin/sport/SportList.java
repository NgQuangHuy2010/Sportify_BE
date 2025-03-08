package com.sportify.service.dtos.admin.sport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SportList {
    private Long id;
	private String sportName;
	private String imageUrl;
	
	public SportList(Long id, String sportName, String image) {
        this.id = id;
        this.sportName = sportName;
        this.imageUrl = "http://localhost:8080/uploads/sports/" + image; // Đường dẫn đầy đủ
    }
	

	
}

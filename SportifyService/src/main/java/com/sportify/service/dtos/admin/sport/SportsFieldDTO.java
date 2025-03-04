package com.sportify.service.dtos.admin.sport;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SportsFieldDTO {
	private Long id;
    private String name;
    private String type;
    private String size;
    private Double pricePerHour;
    private Boolean isAvailable;
    private Long sportsCenterId;
    private Integer maxPlayers;      // Số người chơi tối đa
    private String endTime;       
    private String startTime;        // Giờ bắt đầu (HH:mm)
    private Integer subPlayers;      // Số người dự bị
    private Date date;
}


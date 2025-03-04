package com.sportify.service.dtos.admin.sport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSportsFieldDTO {
    private String name;
    private String type;
    private String size;
    private Double pricePerHour;
    private Boolean isAvailable;
}

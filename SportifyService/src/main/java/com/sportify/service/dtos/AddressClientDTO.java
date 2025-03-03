package com.sportify.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressClientDTO {
	   private String ward;
	    private String district;
	    private String city;
	    private String no;
}

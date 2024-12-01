package com.mytech.sportify.apis.response;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse {
	private String token;
	private String type = "Bearer";
	private String fullname;
	private String email;
	private List<String> roles;
	private String status;
	
	public JWTResponse(String token, String fullname, String email, List<String> roles, String status) {
		super();
		this.token = token;
		this.fullname = fullname;
		this.email = email;
		this.roles = roles;
		this.status = status;
	}
	
	
}

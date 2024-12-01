package com.mytech.sportify.portal.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6594730179564092650L;
	private String email;
	private String password;
}

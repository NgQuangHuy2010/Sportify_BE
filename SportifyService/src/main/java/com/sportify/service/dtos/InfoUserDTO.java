package com.sportify.service.dtos;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import com.sportify.service.entities.Address;
import com.sportify.service.entities.Sport;
import com.sportify.service.entities.UserProfile;
import com.sportify.service.enums.Gender;

public class InfoUserDTO {
	private String firstname;
	private String lastname;
	private String email;
	private Date birthday;
	private String phone;
	 private String avatar;
	private String bio;
	private Gender gender;
	private List<Long> sports;
	private Address address;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<Long> getSports() {
		return sports;
	}

	public void setSports(List<Long> sports) {
		this.sports = sports;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	  public InfoUserDTO(UserProfile user) {
	        this.firstname = user.getFirstname();
	        this.lastname = user.getLastname();
	        this.email = user.getEmail();
	        this.avatar = user.getAvatar();
	        this.birthday = new java.sql.Date(user.getBirthday().getTime());
	        this.phone = user.getPhone();
	        this.bio = user.getBio();
	        this.gender = user.getGender();
	        this.sports = user.getSports().stream()
	                  .map(Sport::getId) // Lấy ID của từng Sport
	                  .collect(Collectors.toList());

	        this.address = user.getAddress();
	    }

}

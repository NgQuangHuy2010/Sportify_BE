package com.sportify.service.dtos;

import java.sql.Date;
import java.util.List;

import com.sportify.service.entities.Address;
import com.sportify.service.enums.Gender;

public class UserProfileRequest {
	private String firstname;
	private String lastname;
	private String email;
	private String password;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<Long> getSports() {
		return sports;
	}

	public void setSports(List<Long> sports) {
		this.sports = sports;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

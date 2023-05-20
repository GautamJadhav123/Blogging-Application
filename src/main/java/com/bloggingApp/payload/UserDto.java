package com.bloggingApp.payload;

import jakarta.validation.constraints.Email; 
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {
	

	private Integer userId;

	@NotEmpty (message = "name can not be empty")
	@Size (min = 4, message = "name should be minimum of 4 characters")
	private String name;
	
	@Email (message = "please enter a valid email")
	private String email;

	@NotEmpty
	@Size (min = 3, max = 10, message = "password must be between 3 to 10 characters")
	private String password;

	@NotEmpty
	private String about;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public UserDto(Integer userId, String name, String email, String password, String about) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}

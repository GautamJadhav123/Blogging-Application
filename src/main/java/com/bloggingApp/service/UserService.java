package com.bloggingApp.service;

import java.util.List;

import com.bloggingApp.payload.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser (UserDto userDto, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUserById(Integer userId);
	
	
}

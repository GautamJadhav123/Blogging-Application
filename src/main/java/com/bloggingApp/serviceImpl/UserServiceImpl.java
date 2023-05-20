package com.bloggingApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingApp.exception.ResourceNotFoundException;
import com.bloggingApp.model.User;
import com.bloggingApp.payload.UserDto;
import com.bloggingApp.repository.UserRepository;
import com.bloggingApp.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		User savedUser = this.userRepository.save(user);
		return this.modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
			
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepository.save(user);
				
		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException ("User", "Id", userId ));
			
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map((user) -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUserById(Integer userId) {

		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepository.delete(user);
		
	}

	
	
}

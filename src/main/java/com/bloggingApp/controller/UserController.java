package com.bloggingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingApp.payload.ApiResponse;
import com.bloggingApp.payload.UserDto;
import com.bloggingApp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/blogging-api/users")
public class UserController {
	
		@Autowired
		private UserService userService;
	
		//Creating User
		@PostMapping ("/create")
		public ResponseEntity<UserDto> createUser (@Valid @RequestBody UserDto userDto){
			
			UserDto userDtoCreated = this.userService.createUser(userDto);			
			return new ResponseEntity<> (userDtoCreated, HttpStatus.CREATED);
			
		}
		
		//Updating User
		@PutMapping ("/update/{userId}")
		public ResponseEntity<UserDto> updateUser (@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
			
			UserDto userUpdated = this.userService.updateUser(userDto, userId);
			return ResponseEntity.ok(userUpdated);
		}
		
		//Deleting User
		@DeleteMapping ("/delete/{userId}")
		public ResponseEntity<ApiResponse>	deleteUserById (@PathVariable Integer userId){
			this.userService.deleteUserById(userId);
			return new ResponseEntity<ApiResponse>(new ApiResponse ("User Deleted Successfully", true), HttpStatus.OK); 
			
		}
		
		//Fetching all Users
		@GetMapping ("/getall")
		public ResponseEntity<List<UserDto>> getAllUsers(){
			List<UserDto> userList = this.userService.getAllUsers();
			return new ResponseEntity<> (userList, HttpStatus.OK);
		
		}
		
		//Fetching single User
		@GetMapping ("/getuser/{userId}")
		public ResponseEntity<UserDto> getUserById (@PathVariable Integer userId){
			
			UserDto userDto = this.userService.getUserById(userId);
			return new ResponseEntity<> (userDto, HttpStatus.OK);
		}
		
		
}

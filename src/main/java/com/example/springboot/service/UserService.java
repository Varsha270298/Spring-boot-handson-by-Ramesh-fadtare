package com.example.springboot.service;

import java.util.List;

import com.example.springboot.dto.UserDto;
import com.example.springboot.entity.User;

public interface UserService {
	
	

	UserDto createUser(UserDto user);
	
	UserDto getUserById(Long userId);
	
	List<UserDto> getAllUsers();
	
	UserDto updateUser(UserDto user);
	
	void deleteUser(Long userId);
	
	

}

package com.example.springboot.mapper;

import com.example.springboot.dto.UserDto;
import com.example.springboot.entity.User;

public class UserMapper {
	//convert UserJPA Entity into UserDto
	public static UserDto mapToUserDto(User user) {
		//convert userJpa to userDto 
		UserDto userDto = new UserDto(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail()
				);
		
		return userDto;
		
	}
	//convert userdto entity intio user jpa entity
	
	public static User mapToUser(UserDto userDto) {
		User user = new User(
				userDto.getId(),
				userDto.getFirstName(),
				userDto.getLastName(),
				userDto.getEmail()
				);
		
		
		
		return user;
		
	}

}

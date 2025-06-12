package com.example.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.springboot.dto.UserDto;
import com.example.springboot.entity.User;

@Mapper
public interface AutoUserMapper {

	
	AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
	UserDto mapToUserDto(User user);
	
	User mapToUser(UserDto userDto);
	
}

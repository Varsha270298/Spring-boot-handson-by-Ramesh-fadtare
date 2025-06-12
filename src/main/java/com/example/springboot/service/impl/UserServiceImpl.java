package com.example.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.springboot.dto.UserDto;
import com.example.springboot.entity.User;
import com.example.springboot.exception.EmailAlreadyExistsException;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.mapper.AutoUserMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	
	private UserRepository userRepository;
	 private ModelMapper modelMapper;
	 
	
	public UserDto createUser(UserDto userDto) {
		//convert userdto to user jpa
		//User user = UserMapper.mapToUser(userDto);
		//User user = modelMapper.map(userDto, User.class);
		
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
		
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email already Exists for User");
		}
		
		User user = AutoUserMapper.MAPPER.mapToUser(userDto);

	User savedUser = userRepository.save(user);
	
	//convert user Jpa entity to userdto
	//UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
	
	//UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
	UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

	return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long userId) {
		//Optional<User> optionalUser = userRepository.findById(userId);
		User user = userRepository.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User","id",userId) 
				);
			
		

		//User user = optionalUser.get();
		//return UserMapper.mapToUserDto(user);
		//return modelMapper.map(user,UserDto.class);
		//return AutoUserMapper.MAPPER.mapToUserDto(optionalUser.get());
		return AutoUserMapper.MAPPER.mapToUserDto(user);

	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users= userRepository.findAll();
		//return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
		
		//return users.stream().map((user)->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
	return users.stream().map((user)->AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());

	}

	@Override
	public UserDto updateUser(UserDto user) {
		//User existingUser = userRepository.findById(user.getId()).get();
		User existingUser = userRepository.findById(user.getId()).orElseThrow(
				()-> new ResourceNotFoundException("User","id",user.getId())
				);

		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());;
		User updatedUser = userRepository.save(existingUser);
		
		//return UserMapper.mapToUserDto(updatedUser);
		
		//return modelMapper.map(updatedUser, UserDto.class);
		return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);

	}

	@Override
	public void deleteUser(Long userId) {
		User existingUser = userRepository.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User","id",userId)
				);

		userRepository.deleteById(userId);
		
	}
	

}

package com.example.springboot.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.springboot.dto.UserDto;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ErrorDetails;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
	
	
	
	private UserService userService;
	
	//build create user restAPI 
	//for model mapper create user we can use this type of url http://localhost:8080/api/users
	@PostMapping

	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
	
		UserDto savedUser = userService.createUser(user);
		return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
		
	}
	
	//build rest api by get userID 
	//http://localhost:8080/api/users/1
	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUserbyId(@PathVariable("id") Long  userId){
		UserDto userDto= userService.getUserById(userId);
		return new ResponseEntity<>(userDto,HttpStatus.OK);
		
	}
	
	// Build rest api getall user
	//http://localhost:8080/api/users/
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
		
	}
	
	// build update User REST API
	//http://localhost:8080/api/users/1
	@PutMapping("{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id")Long userId, @RequestBody UserDto user){
		user.setId(userId);
		UserDto updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
	}
	
	//Build Delete User RESTA API
	@DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
    	userService.deleteUser(userId);
		return new ResponseEntity<>("User successfully deleted",HttpStatus.OK);
    	
    }
	
	
	//specific exceptoions handler 
	//http://localhost:8080/api/users/13
	/*@ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                     WebRequest webRequest){
      ErrorDetails errorDetails = new ErrorDetails(
              LocalDateTime.now(),
             exception.getMessage(),
             webRequest.getDescription(false),
             "USER_NOT_FOUND"
     );
      return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		
	}*/
}

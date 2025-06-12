package com.example.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

}

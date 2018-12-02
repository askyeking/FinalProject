package com.skilldistillery.swag.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.swag.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	Boolean existsByEmail(String email);
	

}

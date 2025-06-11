package com.skilldistillery.cafetracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.cafetracker.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);

}

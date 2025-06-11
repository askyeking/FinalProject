package com.skilldistillery.cafetracker.services;

import com.skilldistillery.cafetracker.entities.User;

public interface AuthService {

	public User register(User user);
	public User getUserByUsername(String username);
	
}

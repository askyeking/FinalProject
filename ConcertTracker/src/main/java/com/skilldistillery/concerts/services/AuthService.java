package com.skilldistillery.concerts.services;

import com.skilldistillery.concerts.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);


}

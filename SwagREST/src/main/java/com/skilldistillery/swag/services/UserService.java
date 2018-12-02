package com.skilldistillery.swag.services;

import com.skilldistillery.swag.entities.User;

public interface UserService {
	
	User show(int id);
	
	User newUser(User user);
	
	
}

package com.skilldistillery.swag.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserRepository repo;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRepository userRepo;

	// persists a new user object to the database
	@Override
	public User register(User user) {
		if (this.userRepo.existsByEmail(user.getEmail())) {
			return null;
		} else {
		String encodedPW = encoder.encode(user.getPassword());
		user.setPassword(encodedPW);
		
		user.setActive(true);
		user.setRole("standard");

		repo.saveAndFlush(user);
		return user;
		}
	}
}
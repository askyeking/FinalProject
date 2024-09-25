package com.skilldistillery.concerts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.concerts.entities.User;
import com.skilldistillery.concerts.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserRepository userRepo;

	@Override
	public User register(User user) {
//		user.setPassword(encoder.encode(user.getPassword()));
//		user.setEnabled(true);
//		user.setRole("standard");
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}

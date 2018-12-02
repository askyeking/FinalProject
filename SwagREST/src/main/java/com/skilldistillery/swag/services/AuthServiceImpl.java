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

	@Override
	public User register(User user) {
		String encodedPW = encoder.encode(user.getPassword());
		user.setPassword(encodedPW);

		user.setRole("standard");

		repo.saveAndFlush(user);
		return user;
	}
}
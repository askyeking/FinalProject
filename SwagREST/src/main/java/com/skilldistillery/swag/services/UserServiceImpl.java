package com.skilldistillery.swag.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepo;

	@Override
	public User show(int id) {
		User user = null;
		Optional<User> opt = userRepo.findById(id);
		if(opt.isPresent()) {
			user = opt.get();
		}
		
		return user;
	}
	
	
}

package com.skilldistillery.swag.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.repositories.CommentFromCustomerRepository;

@Service
public class CommentFromCustomerServiceImpl implements CommentFromCustomerService {
	@Autowired
	CommentFromCustomerRepository customerCommentRepo;
}

package com.skilldistillery.swag.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.CommentFromCustomer;
import com.skilldistillery.swag.repositories.CommentFromCustomerRepository;

@Service
public class CommentFromCustomerServiceImpl implements CommentFromCustomerService {
	@Autowired
	CommentFromCustomerRepository customerCommentRepo;
	
	@Override
	public CommentFromCustomer persistComment(CommentFromCustomer comment) {
		customerCommentRepo.saveAndFlush(comment);
		
		return comment;
	}
	
	@Override
	public boolean delete(int commentId) {
		customerCommentRepo.deleteById(commentId);
		if (customerCommentRepo.findById(commentId).isPresent()) {
			return false;
		}
		return true;
	}
	
	@Override
	public CommentFromCustomer update(CommentFromCustomer comment) {
		CommentFromCustomer commentUpdated = null;
		Optional <CommentFromCustomer> opt = customerCommentRepo.findById(commentUpdated.getId());
		if (opt.isPresent()) {
			commentUpdated = opt.get();
			commentUpdated.setComment(comment.getComment());
		}
		
		return commentUpdated;
		
	}
}

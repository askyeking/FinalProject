package com.skilldistillery.swag.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.CommentFromVendor;
import com.skilldistillery.swag.repositories.CommentFromVendorRepository;

@Service
public class CommentFromVendorServiceImpl implements CommentFromVendorService {

	@Autowired
	CommentFromVendorRepository vendorCommentRepo;

	// persist a vendor comment
	@Override
	public CommentFromVendor persistComment(CommentFromVendor comment) {
		vendorCommentRepo.saveAndFlush(comment);
		
		return comment;
	}
	
	// delete a vendor comment
	@Override
	public boolean delete(int commentId) {
		vendorCommentRepo.deleteById(commentId);
		if (vendorCommentRepo.findById(commentId).isPresent()) {
			return false;
		}
		return true;
	}
}

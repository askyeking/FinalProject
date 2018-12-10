package com.skilldistillery.swag.services;

import com.skilldistillery.swag.entities.CommentFromVendor;

public interface CommentFromVendorService {

	CommentFromVendor persistComment(CommentFromVendor comment);

	boolean delete(int commentId);
	


}

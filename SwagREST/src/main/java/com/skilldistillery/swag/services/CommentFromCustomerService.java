package com.skilldistillery.swag.services;

import com.skilldistillery.swag.entities.CommentFromCustomer;

public interface CommentFromCustomerService {

	CommentFromCustomer persistComment(CommentFromCustomer comment);

	boolean delete(int commentId);

	CommentFromCustomer update(CommentFromCustomer comment);

}

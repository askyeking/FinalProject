package com.skilldistillery.swag.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.swag.entities.CommentFromVendor;
import com.skilldistillery.swag.entities.ItemRental;
import com.skilldistillery.swag.services.CommentFromVendorService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class CommentFromVendorController {
	
	@Autowired
	CommentFromVendorService vendorCommentService;
	
	@PostMapping("vendorcomment/itemRental/{id}")
	public CommentFromVendor postVendorComment(@PathVariable("id") int itemId, @RequestBody CommentFromVendor comment , HttpServletRequest req, HttpServletResponse res, Principal principal) {
		ItemRental itemPostedTo = new ItemRental();
		itemPostedTo.setId(itemId);
		comment.setItemRental(itemPostedTo);
		
		comment = vendorCommentService.persistComment(comment);
		if (comment != null && comment.getId() != 0) {
			res.setStatus(201);
		}
		else {
			res.setStatus(400);
		}
		
		return comment;
	}
	
	@DeleteMapping("vendorcomment/{id}")
	public String deleteComment(@PathVariable("id") int commentId, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		boolean deleted = vendorCommentService.delete(commentId);
		if (deleted) {
			res.setStatus(200);
			return "Comment deleted";
		}
		res.setStatus(400);
		
		return "comment deletion failed";
	}

}

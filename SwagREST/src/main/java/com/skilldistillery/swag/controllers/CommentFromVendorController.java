package com.skilldistillery.swag.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
		
		System.err.println("Inside PostCommentFromVendor *****************************************************");
		System.out.println(comment);
		comment = vendorCommentService.persistComment(comment);
		
		return comment;
	}

}

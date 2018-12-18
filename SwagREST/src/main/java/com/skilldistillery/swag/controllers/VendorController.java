package com.skilldistillery.swag.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.entities.Vendor;
import com.skilldistillery.swag.services.UserService;
import com.skilldistillery.swag.services.VendorService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class VendorController {
		
	@Autowired
	VendorService vendorService;
	
	@Autowired
	UserService userService;
	
	// retrieve a single vendor
	@GetMapping("vendor/{vid}")
	public Vendor show(@PathVariable("vid") int vid, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		Vendor vendor = vendorService.show(vid);
		if(vendor == null) {
			res.setStatus(404);
		}
		return vendor;
	}
	
	// retrieve a list with all vendors
	@GetMapping("vendor")
	public List<Vendor> showAll(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return this.vendorService.showAll();
	}
	
	//update a vendor's profile information
	@PatchMapping("vendor")
	public User editVendor(@RequestBody Vendor userUpdate, HttpServletRequest req, HttpServletResponse resp,
			Principal principal) {
		
		User originalUser = userService.findByEmail(principal.getName());
		
		if (originalUser != null && userUpdate != null) {
			vendorService.update(userUpdate, originalUser);
			resp.setStatus(200);
			originalUser = userService.findByEmail(principal.getName());
		} else {
			resp.setStatus(400);
		}

		return originalUser;
	}
	
	// retrieve vendors by keyword
	@GetMapping("vendor/search/{keyword}")
	public List<Vendor> vendorSearch(@PathVariable("keyword") String keyword, HttpServletRequest req, HttpServletResponse resp,
			Principal principal ) {
		return this.vendorService.vendorSearch(keyword);
	}
	
	// retrieve the user by their vendor profile id
	@GetMapping("vendor/user/{id}")
	public User retrieveUserObject(@PathVariable("id") int vendorId, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		User itemsVendor = vendorService.getUserByVendorId(vendorId);
		
		if(itemsVendor == null) {
			res.setStatus(404);
		}
		else {
			res.setStatus(200);
		}
		return itemsVendor;
	}


	
}

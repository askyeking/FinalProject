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
	
	@GetMapping("vendor/{vid}")
	public Vendor show(@PathVariable("vid") int vid, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		Vendor vendor = vendorService.show(vid);
		if(vendor == null) {
			res.setStatus(404);
		}
		return vendor;
	}
	
	
	@GetMapping("vendor")
	public List<Vendor> showAll(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return this.vendorService.showAll();
	}
	
	@PatchMapping("vendor")
	public User editVendor(@RequestBody Vendor userUpdate, HttpServletRequest req, HttpServletResponse resp,
			Principal principal) {
		
		System.out.println("PATCH VendorController.editVendor() "
				+ "{api/user/customer}");
		System.out.println(userUpdate.getAbout());
		System.out.println(userUpdate.getDisplayName());
		System.out.println(userUpdate.getImgUrl());
		System.out.println("************************************");
		
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
	
	@GetMapping("vendor/search/{keyword}")
	public List<Vendor> vendorSearch(@PathVariable("keyword") String keyword, HttpServletRequest req, HttpServletResponse resp,
			Principal principal ) {
		return this.vendorService.vendorSearch(keyword);
		
		
		
	}

	
}

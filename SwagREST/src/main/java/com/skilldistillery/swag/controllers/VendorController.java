package com.skilldistillery.swag.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.swag.entities.Vendor;
import com.skilldistillery.swag.services.VendorService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class VendorController {
		
	@Autowired
	VendorService vendorService;
	
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
	
	

	
}

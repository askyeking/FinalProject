package com.skilldistillery.swag.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.swag.entities.ItemRental;
import com.skilldistillery.swag.services.RentalItemService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class RentalItemController {
	
	@Autowired
	RentalItemService rentalService;
	

	@GetMapping("rental")
	public List<ItemRental> index(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		return rentalService.showAll();

	}
	
	@PostMapping("rental/customer/{cid}") 
	public ItemRental newRental(@PathVariable("cid") int cid, HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		return null;
		
	}

}

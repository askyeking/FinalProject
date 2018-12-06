package com.skilldistillery.swag.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.swag.entities.ItemRental;
import com.skilldistillery.swag.services.CustomerService;
import com.skilldistillery.swag.services.InventoryItemService;
import com.skilldistillery.swag.services.RentalItemService;
import com.skilldistillery.swag.services.UserService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class RentalItemController {
	
	@Autowired
	RentalItemService rentalService;
	@Autowired
	UserService userService;
	@Autowired
	CustomerService customerService;
	@Autowired
	InventoryItemService itemService;

	@GetMapping("rental")
	public List<ItemRental> index(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		return rentalService.showAll();
	}
	
//	@PostMapping("rental")
//	public ItemRental itemInCart(@RequestBody ItemRental item, HttpServletRequest req, HttpServletResponse res, Principal principal) {
//		User itemPoster = userService.findByEmail(principal.getName());
//		
//		ItemRental rentItem = rentalService.postItemRental(itemPoster, ItemRental);
//		
//		if (rentItem != null) {
//			res.setStatus(201);
//		}else {
//			res.setStatus(400);
//		}
//		
//		return rentItem;
//	}
	
	@PostMapping("rental")
	public ItemRental rentInventoryItem(@RequestBody ItemRental itemRented, HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		
		
		if(itemRented.getCustomer() != null
				&& itemRented.getInventoryItem() != null
				&& !itemService.showSingleItem(itemRented.getInventoryItem().getId()).isRented()
				&& itemService.showSingleItem(itemRented.getInventoryItem().getId()).isActive()) {
			resp.setStatus(200);
			itemRented = rentalService.postItemRental(itemRented);
		}
		else {
			resp.setStatus(406);
			itemRented = null;
		}
		
		return itemRented;
	}
	
	@PatchMapping("rental")
	public ItemRental returnInventoryItem(@RequestBody ItemRental itemRented, HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		
		
		if(itemRented.getCustomer() != null
				&& itemRented.getInventoryItem() != null
				&& itemRented.getInventoryItem().isActive()
				&& itemRented.getId() != 0) {
			
			itemRented = rentalService.returnItemRental(itemRented);
			resp.setStatus(200);
			
		}
		else {
			resp.setStatus(406);
		}
		
		return itemRented;
	}
	
	

}

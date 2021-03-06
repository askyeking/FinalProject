package com.skilldistillery.swag.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.swag.entities.ItemRental;
import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.services.CustomerService;
import com.skilldistillery.swag.services.InventoryItemService;
import com.skilldistillery.swag.services.ItemRentalService;
import com.skilldistillery.swag.services.UserService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class ItemRentalController {

	@Autowired
	ItemRentalService rentalService;
	@Autowired
	UserService userService;
	@Autowired
	CustomerService customerService;
	@Autowired
	InventoryItemService itemService;

	// route to view all the items that are rented or in the rental transaction
	@GetMapping("rental")
	public List<ItemRental> index(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		return rentalService.showAll();
	}

	// route that retrieves a specific item being rented or on the rental transaction
	@GetMapping("rental/{id}")
	public ItemRental getItemRenta(@PathVariable("id") int itemId, HttpServletRequest req, HttpServletResponse resp,
			Principal principal) {
		ItemRental itemRequested = rentalService.getOne(itemId);

		if (itemRequested != null) {
			resp.setStatus(200);
		} else {
			resp.setStatus(400);
		}

		return itemRequested;
	}
	
	// retrieves all rentals made by a specific customer (takes the id of the customer)
	@GetMapping("rental/customer/{id}")
	public List<ItemRental> getRentalsByCustomer(@PathVariable("id") int customerId, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {
		List<ItemRental> rentals = rentalService.getCustomersRentalHistory(customerId);
		Collections.sort(rentals, new Comparator<ItemRental>() {
			public int compare(ItemRental i1, ItemRental i2) {
				return -((Boolean) i1.isActive()).compareTo((Boolean) i2.isActive());
			}
		});
		return rentals;
	}

	// retrieve all rentals of a specific inventory item
	@GetMapping("rental/item/{id}")
	public List<ItemRental> getRentalsByItem(@PathVariable("id") int itemId, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {
		return rentalService.getItemRentalHistory(itemId);
	}

	// persists a new rental of an inventory item
	@PostMapping("rental")
	public ItemRental rentInventoryItem(@RequestBody ItemRental itemRented, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {
		User rentingUser = userService.findByEmail(principal.getName());
		itemRented.setCustomer(rentingUser.getCustomer());

		if (itemRented.getCustomer() != null && itemRented.getInventoryItem() != null
				&& !itemService.showSingleItem(itemRented.getInventoryItem().getId()).isRented()
				&& itemService.showSingleItem(itemRented.getInventoryItem().getId()).isActive()) {
			resp.setStatus(200);
			itemRented = rentalService.postItemRental(itemRented);
		} else {
			resp.setStatus(406);
			itemRented = null;
		}

		return itemRented;
	}
	
	// edits an inventory item and rental objects so that they are mark as "returned" when a customer
	// returns an item they rented.
	@PatchMapping("rental")
	public ItemRental returnInventoryItem(@RequestBody ItemRental itemRented, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {

		itemRented.setCustomer(null);
		User rentingUser = userService.findByEmail(principal.getName());
		itemRented.setCustomer(rentingUser.getCustomer());

		if (itemRented.getCustomer() != null && itemRented.getInventoryItem() != null
				&& itemRented.getInventoryItem().isActive() && itemRented.getId() != 0) {

			itemRented = rentalService.returnItemRental(itemRented);
			resp.setStatus(200);

		} else {
			resp.setStatus(406);
		}

		return itemRented;
	}

}

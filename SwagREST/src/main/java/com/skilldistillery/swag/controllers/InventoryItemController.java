package com.skilldistillery.swag.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.swag.entities.InventoryItem;
import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.entities.Vendor;
import com.skilldistillery.swag.services.InventoryItemService;
import com.skilldistillery.swag.services.UserService;
import com.skilldistillery.swag.services.VendorService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class InventoryItemController {
//	
//	TODO Create a method that returns items posted by the principal user's vendor
//	
	@Autowired
	InventoryItemService itemService;
	@Autowired
	VendorService vendorService;
	@Autowired
	UserService userService;
	
	
	
	@GetMapping("items")
	public Set<InventoryItem> indexCustomer(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		return itemService.indexCustomer();
	}
	
	@GetMapping("itemslist")
	public List<InventoryItem> showAllItems(HttpServletRequest req, HttpServletResponse resp/*, Principal principal*/) {
		return itemService.indexItems();
	}
	
	@GetMapping("item/{id}")
	public InventoryItem showSingleItem(@PathVariable("id") int id, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		InventoryItem item = itemService.showSingleItem(id);
		if(item == null) {
			res.setStatus(404);
		}
		else {
			res.setStatus(200);
		}
		return item;
	}
	
	@PostMapping("item") 
	public InventoryItem newItem(@RequestBody InventoryItem item, HttpServletResponse res, HttpServletRequest req, Principal principal) {
		
		User itemPoster = userService.findByEmail(principal.getName());
		
		
		InventoryItem addItem = itemService.postItem(item, itemPoster);
		if (addItem != null) {
			res.setStatus(201);
		}else {
			res.setStatus(400);
		}
		return addItem;
		
	}
	
	
	@GetMapping("item/vendor")
	public List<InventoryItem> vendorItems(HttpServletResponse res, HttpServletRequest req, Principal principal) {
		
		Vendor vendor = userService.findByEmail(principal.getName()).getVendor();
		List<InventoryItem> vendorItems = this.itemService.indexVendor(vendor);
		return vendorItems;
		
	}
	

}

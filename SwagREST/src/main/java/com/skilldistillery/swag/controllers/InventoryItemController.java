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
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("vendor/profile/{id}")
	public User retrieveVendor(@PathVariable("id") int itemId, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		User itemsVendor = itemService.showVendor(itemId);
		
		if(itemsVendor == null) {
			res.setStatus(404);
		}
		else {
			res.setStatus(200);
		}
		return itemsVendor;
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
	
	@PutMapping("item/{id}")
	public InventoryItem updateItem(@PathVariable ("id") int id, @RequestBody InventoryItem item,
			HttpServletResponse res, HttpServletRequest req, Principal principal) {
		User userUpdatingItem = userService.findByEmail(principal.getName());
		InventoryItem updateItem = itemService.update(userUpdatingItem, id, item);
		return updateItem;
	}
	
	
	@GetMapping("item/vendor")
	public List<InventoryItem> vendorItems(HttpServletResponse res, HttpServletRequest req, Principal principal) {
		Vendor vendor = userService.findByEmail(principal.getName()).getVendor();
		List<InventoryItem> vendorItems = this.itemService.indexVendor(vendor);
		return vendorItems;
	}
	
	@GetMapping("item/user/{id}")
	public User getPostingUser(@PathVariable("id") int itemId, HttpServletResponse res, HttpServletRequest req, Principal principal) {
		this.itemService.showItemOwner(itemId);
		return null;
	}
	
	
	
	
	@GetMapping("items/category/{category}")
	public List<InventoryItem> itemsByCategory(@PathVariable("category") String category, HttpServletResponse res, HttpServletRequest req, Principal principal) {
		return this.itemService.findByCategory(category);
	}
	
	@GetMapping("items/name/{keyword}")
	public List<InventoryItem> itemsByKeyword(@PathVariable("keyword") String keyword, HttpServletResponse res, HttpServletRequest req, Principal principal) {
		List<InventoryItem> items = this.itemService.findByKeyword(keyword);
		System.err.println(items);
		return items;
	}
	
	
	
	
	
	

}

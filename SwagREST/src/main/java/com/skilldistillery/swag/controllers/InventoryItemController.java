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
	
	
	// retrieve all inventory items
	@GetMapping("items")
	public Set<InventoryItem> indexCustomer(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		return itemService.indexCustomer();
	}
	
	// route that retrieves a specific vendor profile based on the ID provided.
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

	// route that retrieves the list of the entire InventoryItems
	@GetMapping("itemslist")
	public List<InventoryItem> showAllItems(HttpServletRequest req, HttpServletResponse resp/*, Principal principal*/) {
		return itemService.indexItems();
	}
	
	// route that retrieves a specific InventoryItem with an id as a path variable
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
	
	// route to create an itemInventory and persist it to the database
	@PostMapping("item") 
	public InventoryItem newItem(@RequestBody InventoryItem item, HttpServletResponse res, HttpServletRequest req, Principal principal) {
		if(item.getImgUrl() == null || item.getImgUrl().equals("")) {
			item.setImgUrl("https://feedingsouthflorida.org/wp-content/uploads/2015/09/image-pending.gif");
		}
		
		User itemPoster = userService.findByEmail(principal.getName());
		InventoryItem addItem = itemService.postItem(item, itemPoster);
		if (addItem != null) {
			res.setStatus(201);
		}else {
			res.setStatus(400);
		}
		return addItem;
	}
	
	// route that is used to update/edit a specific inventoryItem
	@PutMapping("item/{id}")
	public InventoryItem updateItem(@PathVariable ("id") int id, @RequestBody InventoryItem item,
			HttpServletResponse res, HttpServletRequest req, Principal principal) {
		User userUpdatingItem = userService.findByEmail(principal.getName());
		InventoryItem updateItem = itemService.update(userUpdatingItem, id, item);
		return updateItem;
	}
	
	// route that retrieves a list of inventoryItems based on the vendor(user) that owns/posted them
	@GetMapping("item/vendor")
	public List<InventoryItem> vendorItems(HttpServletResponse res, HttpServletRequest req, Principal principal) {
		Vendor vendor = userService.findByEmail(principal.getName()).getVendor();
		List<InventoryItem> vendorItems = this.itemService.indexVendor(vendor);
		return vendorItems;
	}
	
	// route that retrieves a specific user based on the posted item
	@GetMapping("item/user/{id}")
	public User getPostingUser(@PathVariable("id") int itemId, HttpServletResponse res, HttpServletRequest req, Principal principal) {
		this.itemService.showItemOwner(itemId);
		return null;
	}
	
	// route for search functionality based on inventoryItem category (i.e. suit, pants, etc...)
	@GetMapping("items/category/{category}")
	public List<InventoryItem> itemsByCategory(@PathVariable("category") String category, HttpServletResponse res, HttpServletRequest req, Principal principal) {
		return this.itemService.findByCategory(category);
	}
	
	// roiute for search functionality using keywords. Works on both "name" and "vendor"
	@GetMapping("items/name/{keyword}")
	public List<InventoryItem> itemsByKeyword(@PathVariable("keyword") String keyword, HttpServletResponse res, HttpServletRequest req, Principal principal) {
		List<InventoryItem> items = this.itemService.findByKeyword(keyword);
		System.err.println(items);
		return items;
	}
		
	

}

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.swag.entities.InventoryItem;
import com.skilldistillery.swag.services.InventoryItemService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class InventoryItemController {
	
	@Autowired
	InventoryItemService itemService;
	
	
	@GetMapping("items")
	public Set<InventoryItem> indexCustomer(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		return itemService.indexCustomer();
	}
	
	@GetMapping("itemslist")
	public List<InventoryItem> showAllItems(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		return itemService.indexItems();
	}
	
	@GetMapping("item/{id}")
	public InventoryItem showSingleItem(@PathVariable("id") int id, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		InventoryItem item = itemService.showSingleItem(id);
		if(item == null) {
			res.setStatus(404);
		}
		return item;
	}
	
	
		
//	@RequestMapping(path = "shaun/todos", method = RequestMethod.GET)
//	public Set<Todo> showAllTodos(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
//		return todoService.index(principal.getName());
//	}
}

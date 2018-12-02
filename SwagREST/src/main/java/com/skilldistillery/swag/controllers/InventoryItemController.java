package com.skilldistillery.swag.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public Set<InventoryItem> index(HttpServletRequest req, HttpServletResponse resp) {
		return itemService.indexCustomer();
	}
	
//	@RequestMapping(path = "shaun/todos", method = RequestMethod.GET)
//	public Set<Todo> showAllTodos(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
//		return todoService.index(principal.getName());
//	}
}

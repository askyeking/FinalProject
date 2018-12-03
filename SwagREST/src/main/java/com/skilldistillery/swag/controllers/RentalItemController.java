package com.skilldistillery.swag.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.security.Principal;
import java.util.List;

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

import com.skilldistillery.swag.entities.Customer;
import com.skilldistillery.swag.entities.ItemRental;
import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.services.CustomerService;
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

	@GetMapping("rental")
	public List<ItemRental> index(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		return rentalService.showAll();
	}
	
	

}

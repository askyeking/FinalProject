package com.skilldistillery.swag.controllers;

import java.security.Principal;

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
import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.services.UserService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class UserController {
	@Autowired
	UserService userService;
	

	
	@GetMapping("user/{id}")
	public User showUser(@PathVariable("id") int id, HttpServletRequest req, HttpServletResponse resp,
			Principal principal) {

		// Should also have principal.getEmail or sth...
		User user = userService.show(id);
		if (user != null) {
			resp.setStatus(200);
		} else {
			resp.setStatus(404);
		}

		return user;
	}
	
	@GetMapping("user/email") 
	public User showUserByEmail(HttpServletRequest req, HttpServletResponse resp,
			Principal principal) {

		User user = userService.findByEmail(principal.getName());
		
		if (user != null) {
			resp.setStatus(200);
		} else {
			resp.setStatus(404);
		}

		return user;
	}
	
	@PostMapping("user/register")
	public User addUserCustomerOrVendor(@RequestBody User user, HttpServletRequest req, HttpServletResponse resp,
			Principal principal ) {
			
			System.out.println("User entered");
			System.out.println(user);
			
			
			if (user.getCustomer() != null) {
			System.out.println(user.getCustomer().getDisplayName());
			}
			else {
				System.out.println("not found customer");
			}
			
			if(user.getVendor() != null) {
				System.out.println("vendor");
				System.out.println(user.getVendor().getDisplayName());
			}
			else {
				System.out.println("not found vendor");
			}
			
			User controlUser = userService.findByEmail(principal.getName());
//			User controlUser = this.userService.show(uid);
 			if(controlUser.getEmail().equals(principal.getName())) {
				if(user.getCustomer() != null) {
					this.userService.addCustomer(user.getCustomer(), controlUser.getID());
				}
				if(user.getVendor() != null) {
					this.userService.addVendor(user.getVendor(), controlUser.getID());
				}
				resp.setStatus(201);
				controlUser = userService.findByEmail(principal.getName());
				return controlUser;
			}
			else {
				resp.setStatus(401);
				return null;
			}
	}



	
	

	
	
}
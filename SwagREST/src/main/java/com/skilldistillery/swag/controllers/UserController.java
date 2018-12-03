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
	
	@PostMapping("user/{uid}/register")
	public User addUserCustomerOrVendor(@RequestBody User user, @PathVariable("uid") int uid , HttpServletRequest req, HttpServletResponse resp,
			Principal principal ) {
			User controlUser = this.userService.show(uid);
			if(controlUser.getEmail() == principal.getName()) {
				if(user.getCustomer() != null) {
					this.userService.addCustomer(user.getCustomer(), uid);
				}
				if(user.getVendor() != null) {
					this.userService.addVendor(user.getVendor(), uid);
				}
				resp.setStatus(201);
				return user;
			}
			else {
				resp.setStatus(401);
				return null;
			}
		
			
		
	}



	
	

	
	
}
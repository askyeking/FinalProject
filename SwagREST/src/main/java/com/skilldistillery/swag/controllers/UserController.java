package com.skilldistillery.swag.controllers;

import java.security.Principal;

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

import com.skilldistillery.swag.entities.Customer;
import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.entities.Vendor;
import com.skilldistillery.swag.services.UserService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class UserController {
	@Autowired
	UserService userService;
	
	// shows a single user (takes user id path variable)
	@GetMapping("user/{id}")
	public User showUser(@PathVariable("id") int id, HttpServletRequest req, HttpServletResponse resp,
			Principal principal) {

		User user = userService.show(id);
		if (user != null) {
			resp.setStatus(200);
		} else {
			resp.setStatus(404);
		}

		return user;
	}
	
	//route for getting logged in user
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
	
	// edits a customer profile of a user
	@PatchMapping("user/customer")
	public User editUserCustomer(@RequestBody User userUpdate, HttpServletRequest req, HttpServletResponse resp,
			Principal principal) {
		
		User originalUser = userService.findByEmail(principal.getName());
		
		
		if (originalUser != null && userUpdate != null) {
			userService.update(userUpdate, originalUser);
			resp.setStatus(200);
			originalUser = userService.findByEmail(principal.getName());
		} else {
			resp.setStatus(400);
		}

		return originalUser;
	}
	
	// create a new customer or customer and vendor profile
	@PostMapping("user/register")
	public User addUserCustomerOrVendor(@RequestBody User user, HttpServletRequest req, HttpServletResponse resp,
			Principal principal ) {
		
			if (user != null && user.getCustomer() != null
					&& user.getCustomer().getAvatarURL() == null) {
				user.getCustomer().setAvatarURL("https://feedingsouthflorida.org/wp-content/uploads/2015/09/image-pending.gif");
			}
			
			User controlUser = userService.findByEmail(principal.getName());
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
	
	// create a new vendor profile
	// this method is used when a customer is originally registered just as a user, but later decides to also become a vendor.
	@PostMapping("user/register/vendor")
	public User addVendorProfile(@RequestBody Vendor vendorProfileToAdd, HttpServletRequest req, HttpServletResponse resp,
			Principal principal ) {
		
			System.err.println("Inside Vendor");
			if (vendorProfileToAdd != null && (vendorProfileToAdd.getImgUrl() == null)){
				vendorProfileToAdd.setImgUrl("https://feedingsouthflorida.org/wp-content/uploads/2015/09/image-pending.gif");
			}
			
			User postingUser = userService.findByEmail(principal.getName());
			
 			if(postingUser.getVendor() == null) {
				this.userService.addVendor(vendorProfileToAdd, postingUser.getID());
				resp.setStatus(201);
				postingUser = userService.findByEmail(principal.getName());
				return postingUser;
			}
			else {
				resp.setStatus(401);
				return null;
			}
	}



	
	

	
	
}
package com.skilldistillery.swag.services;

import com.skilldistillery.swag.entities.Customer;
import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.entities.Vendor;

public interface UserService {
	
	User show(int id);
	
	User newUser(User user);
	
	User addCustomer(Customer customer);
	
	User addVendor(Vendor vendor);
	
	
	
}

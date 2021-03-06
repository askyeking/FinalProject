package com.skilldistillery.swag.services;

import java.util.List;

import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.entities.Vendor;

public interface VendorService {
	
	public Vendor show(int vid);

	List<Vendor> showAll();
	
	void update(Vendor userUpdate, User originalUser);

	List<Vendor> vendorSearch(String keyword);
	
	User getUserByVendorId(int vendorId);

}

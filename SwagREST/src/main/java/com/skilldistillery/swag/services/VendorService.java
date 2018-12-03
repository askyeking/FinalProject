package com.skilldistillery.swag.services;

import java.util.List;

import com.skilldistillery.swag.entities.Vendor;

public interface VendorService {
	
	public Vendor show(int vid);

	List<Vendor> showAll(); 

}

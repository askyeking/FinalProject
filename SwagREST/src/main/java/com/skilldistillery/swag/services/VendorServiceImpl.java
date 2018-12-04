package com.skilldistillery.swag.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.Vendor;
import com.skilldistillery.swag.repositories.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {
	
	@Autowired
	VendorRepository vendorRepo;

	@Override
	public Vendor show(int vid) {
		Vendor vendor = null;
		Optional<Vendor> vendorOpt = vendorRepo.findById(vid);
		if(vendorOpt.isPresent()) {
			vendor = vendorOpt.get();
		}
		return vendor;
	}
	
	@Override
	public List<Vendor> showAll() {
		
		//vendorRepo.
		
		return this.vendorRepo.findAll();
	}
	
	
	

}

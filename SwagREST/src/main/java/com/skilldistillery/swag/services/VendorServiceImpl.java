package com.skilldistillery.swag.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.entities.Vendor;
import com.skilldistillery.swag.repositories.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {
	
	@Autowired
	VendorRepository vendorRepo;

	// find a single vendor by id
	@Override
	public Vendor show(int vid) {
		Vendor vendor = null;
		Optional<Vendor> vendorOpt = vendorRepo.findById(vid);
		if(vendorOpt.isPresent()) {
			vendor = vendorOpt.get();
		}
		return vendor;
	}
	
	// retrieve all vendors from the DB
	@Override
	public List<Vendor> showAll() {
		
		//vendorRepo.
		
		return this.vendorRepo.findAll();
	}

	// update fields of an existing vendor object
	@Override
	public void update(Vendor userUpdate, User originalUser) {
		Vendor toUpdateVendor = originalUser.getVendor();
		if (userUpdate.getAbout() != null 
				&& !userUpdate.getAbout().equals("")) {
			toUpdateVendor.setAbout(userUpdate.getAbout());
		}
		if (userUpdate.getDisplayName() != null
				&& !userUpdate.getDisplayName().equals("")) {
			toUpdateVendor.setDisplayName(userUpdate.getDisplayName());
		}
		if(userUpdate.getImgUrl() != null
				&& !userUpdate.getImgUrl().equals("")) {
			toUpdateVendor.setImgUrl(userUpdate.getImgUrl());
		}
		
		vendorRepo.saveAndFlush(toUpdateVendor);
	}
	
	// find a vendor by a keyword in their name
	@Override
	public List<Vendor> vendorSearch(String keyword) {
		return this.vendorRepo.findByDisplayNameContains(keyword);
	}

	// find a user object by ther vendor profile's id
	@Override
	public User getUserByVendorId(int vendorId) {
		User vendorsUser = null;
		Optional<Vendor> opt = vendorRepo.findById(vendorId);
		if (opt.isPresent()) {
			vendorsUser = opt.get().getUser();
		}
		
		return vendorsUser;
	}
	
	
	

}

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
		
		System.out.println(toUpdateVendor);
		vendorRepo.saveAndFlush(toUpdateVendor);
	}
	
	@Override
	public List<Vendor> vendorSearch(String keyword) {
		return this.vendorRepo.findByDisplayNameContains(keyword);
	}
	
	
	

}

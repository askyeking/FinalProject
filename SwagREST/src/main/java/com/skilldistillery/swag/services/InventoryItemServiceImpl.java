package com.skilldistillery.swag.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.InventoryItem;
import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.repositories.InventoryItemRepository;
import com.skilldistillery.swag.repositories.UserRepository;
import com.skilldistillery.swag.repositories.VendorRepository;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {
	
	@Autowired
	InventoryItemRepository itemRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	VendorRepository vendorRepo;

	@Override
	public Set<InventoryItem> indexCustomer() {
		Set<InventoryItem> allAvailableItems = itemRepo.findByIsRentedAndIsActive(false, true);
		return allAvailableItems;
	}

	@Override
	public Set<InventoryItem> indexVendor(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InventoryItem> indexItems() {
		return itemRepo.findAll();
	}

	@Override
	public InventoryItem showSingleItem(int id) {
		InventoryItem item = null;
		Optional<InventoryItem> itemOpt = itemRepo.findById(id);
		if(itemOpt.isPresent()) {
			item = itemOpt.get();
		}
		return item;
	}

	@Override
	public InventoryItem postItem(InventoryItem item, String email) {
		User user = userRepo.findByEmail(email);
		item.setVendor(user.getVendor());
		item.getVendor().setUser(user);
		itemRepo.saveAndFlush(item);
		return item;
	}
	
	  

}

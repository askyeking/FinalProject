package com.skilldistillery.swag.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.InventoryItem;
import com.skilldistillery.swag.repositories.InventoryItemRepository;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {
	
	@Autowired
	InventoryItemRepository itemRepo;

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
	
	  

}

package com.skilldistillery.swag.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.InventoryItem;
import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.entities.Vendor;
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

	// retrieve all inventory items that are not rented and not removed
	@Override
	public Set<InventoryItem> indexCustomer() {
		Set<InventoryItem> allAvailableItems = itemRepo.findByIsRentedAndIsActive(false, true);
		return allAvailableItems;
	}

	// retrieve inventory items offered by a specific vendor
	@Override
	public List<InventoryItem> indexVendor(Vendor vendor) {
		return this.itemRepo.findByVendor(vendor);
		
	}

	//retrieve all inventory items
	@Override
	public List<InventoryItem> indexItems() {
		return itemRepo.findAll();
	}
	
	// retrieve user who posted a specific item as a vendor
	@Override
	public User showItemOwner(int itemId) {
		User postingUser = null;
		InventoryItem itemOwned = showSingleItem(itemId);
		if(itemOwned != null) {
			postingUser = itemOwned.getVendor().getUser();
		}
		
		return postingUser;
	}

	// retrieve a single item by id
	@Override
	public InventoryItem showSingleItem(int id) {
		InventoryItem item = null;
		Optional<InventoryItem> itemOpt = itemRepo.findById(id);
		if(itemOpt.isPresent()) {
			item = itemOpt.get();
		}
		return item;
	}

	// post a new item
	@Override
	public InventoryItem postItem(InventoryItem item, User itemPoster) {
		
		Vendor itemVendor = itemPoster.getVendor();
		item.setVendor(itemVendor);
		item.setRented(false);
		item.setActive(true);
		itemRepo.saveAndFlush(item);
		return item;
	}

	// update an inventory item
	@Override
	public InventoryItem update(User poster, int id, InventoryItem item) {
		InventoryItem managedItem = null;
		Optional<InventoryItem> itemOpt = itemRepo.findById(id);
		if(itemOpt.isPresent()) {
			managedItem = itemOpt.get();
			managedItem.setName(item.getName());
			managedItem.setDescription(item.getDescription());
			managedItem.setPrice(item.getPrice());
			managedItem.setCondition(item.getCondition());
			managedItem.setImgUrl(item.getImgUrl());
			managedItem.setActive(item.isActive());
			managedItem.setRented(item.isRented());
			itemRepo.saveAndFlush(managedItem);
			
		}
		return managedItem;
	}

	// find all items that belong to a specific category
	@Override
	public List<InventoryItem> findByCategory(String category) {
		return this.itemRepo.findByItemCategory(category);
	}
	
	// find all items whose name contains a keyword
	@Override
	public List<InventoryItem> findByKeyword(String keyword) {
		return this.itemRepo.findByNameContains(keyword);
	}

	// find a vendor profile by item's id
	@Override
	public User showVendor(int itemId) {
		User postingUser = null;
		InventoryItem itemOwned = showSingleItem(itemId);
		if(itemOwned != null) {
			postingUser = itemOwned.getVendor().getUser();
		}
		
		return postingUser;
	}
	  

}

package com.skilldistillery.swag.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.swag.entities.InventoryItem;
import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.entities.Vendor;

public interface InventoryItemService {
	
	public Set<InventoryItem> indexCustomer();
	
	public List<InventoryItem> indexVendor(Vendor vendor);
	
	public List<InventoryItem> indexItems();

	public InventoryItem showSingleItem(int id);

	public InventoryItem postItem(InventoryItem item, User poster);

	public InventoryItem update(User poster, int tid, InventoryItem item);
//
//	  public boolean destroy(String username, int tid);
}

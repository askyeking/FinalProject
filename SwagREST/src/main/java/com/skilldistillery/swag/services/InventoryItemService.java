package com.skilldistillery.swag.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.swag.entities.InventoryItem;

public interface InventoryItemService {
	
	public Set<InventoryItem> indexCustomer();
	
	public Set<InventoryItem> indexVendor(String email);
	
	public List<InventoryItem> indexItems();

	public InventoryItem showSingleItem(int id);

	public InventoryItem postItem(InventoryItem item, String email);

//	  public Todo update(String username, int tid, Todo todo);
//
//	  public boolean destroy(String username, int tid);
}

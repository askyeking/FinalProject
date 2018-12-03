package com.skilldistillery.swag.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.swag.entities.InventoryItem;
import com.skilldistillery.swag.entities.User;

public interface InventoryItemService {
	
	public Set<InventoryItem> indexCustomer();
	
	public Set<InventoryItem> indexVendor(String email);
	
	public List<InventoryItem> indexItems();

	public InventoryItem showSingleItem(int id);

	public InventoryItem postItem(InventoryItem item, User poster);

//	  public Todo update(String username, int tid, Todo todo);
//
//	  public boolean destroy(String username, int tid);
}

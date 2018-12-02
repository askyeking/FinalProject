package com.skilldistillery.swag.services;

import java.util.Set;

import com.skilldistillery.swag.entities.InventoryItem;

public interface InventoryItemService {
	public Set<InventoryItem> indexCustomer();
	public Set<InventoryItem> indexVendor(String email);

//	  public Todo show(String username, int tid);
//
//	  public Todo create(String username, Todo todo);
//
//	  public Todo update(String username, int tid, Todo todo);
//
//	  public boolean destroy(String username, int tid);
}

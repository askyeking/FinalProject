package com.skilldistillery.swag.services;

import java.util.List;

import com.skilldistillery.swag.entities.ItemRental;
import com.skilldistillery.swag.entities.User;

public interface RentalItemService {

	List<ItemRental> showAll();

	ItemRental addRental(ItemRental rentedItem, User loggedInUser);
	
	
	
	
}

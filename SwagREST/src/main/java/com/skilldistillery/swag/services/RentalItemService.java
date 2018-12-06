package com.skilldistillery.swag.services;

import java.util.List;

import com.skilldistillery.swag.entities.ItemRental;

public interface RentalItemService {

	List<ItemRental> showAll();


	ItemRental postItemRental(ItemRental itemRented);


	ItemRental returnItemRental(ItemRental itemRented);

	
	
	
	
}

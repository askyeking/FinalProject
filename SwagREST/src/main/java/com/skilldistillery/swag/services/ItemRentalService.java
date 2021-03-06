package com.skilldistillery.swag.services;

import java.util.List;

import com.skilldistillery.swag.entities.ItemRental;

public interface ItemRentalService {

	List<ItemRental> showAll();


	ItemRental postItemRental(ItemRental itemRented);


	ItemRental returnItemRental(ItemRental itemRented);


	ItemRental getOne(int id);


	List<ItemRental> getCustomersRentalHistory(int customerId);


	List<ItemRental> getItemRentalHistory(int itemId);

	
	
	
	
}

package com.skilldistillery.swag.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.ItemRental;

public interface RentalItemService {

	List<ItemRental> showAll();
	
	
}

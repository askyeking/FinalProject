package com.skilldistillery.swag.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.ItemRental;
import com.skilldistillery.swag.repositories.RentalItemRepository;
import com.skilldistillery.swag.repositories.UserRepository;

@Service
public class RentalItemServiceImpl implements RentalItemService {

	
	@Autowired
	RentalItemRepository rentalRepo;
	@Autowired
	UserRepository userRepo;
	
	
	@Override
	public List<ItemRental> showAll() {
		return this.rentalRepo.findAll();
	}


	@Override
	public ItemRental postItemRental() {
		// TODO Auto-generated method stub
		return null;
	}


	
	

}

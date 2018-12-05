package com.skilldistillery.swag.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	public ItemRental postItemRental(ItemRental itemRented) {
		itemRented.setActive(true);
		itemRented.setPaid(true);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		if(itemRented.getStartDate() == null) {
			try {
				itemRented.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		rentalRepo.saveAndFlush(itemRented);
		
		return itemRented;
	}


	
	

}

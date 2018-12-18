package com.skilldistillery.swag.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.InventoryItem;
import com.skilldistillery.swag.entities.ItemRental;
import com.skilldistillery.swag.repositories.CustomerRepository;
import com.skilldistillery.swag.repositories.InventoryItemRepository;
import com.skilldistillery.swag.repositories.ItemRentalRepository;
import com.skilldistillery.swag.repositories.UserRepository;

@Service
public class ItemRentalServiceImpl implements ItemRentalService {

	
	@Autowired
	ItemRentalRepository rentalRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	InventoryItemRepository itemRepo;
	@Autowired
	CustomerRepository custRepo;
	
	// retrieve all rentals
	@Override
	public List<ItemRental> showAll() {
		return this.rentalRepo.findAll();
	}

	// persist an itemRental to the DB
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
		itemRented.setCustomer(custRepo.getOne(itemRented.getCustomer().getId()));
		itemRented.setInventoryItem(itemRepo.getOne(itemRented.getInventoryItem().getId()));
		
		InventoryItem originalItem = itemRented.getInventoryItem();
		originalItem.setRented(true);
		
		rentalRepo.saveAndFlush(itemRented);
		
		return itemRented;
	}

	// set an itemRental's active field to false (meaning the item is returned)
	@Override
	public ItemRental returnItemRental(ItemRental itemRented) {
		itemRented.setActive(false);
		itemRented.setPaid(true);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		if(itemRented.getStartDate() == null) {
			try {
				itemRented.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		itemRented.setCustomer(custRepo.getOne(itemRented.getCustomer().getId()));
		itemRented.setInventoryItem(itemRepo.getOne(itemRented.getInventoryItem().getId()));
		itemRented.getInventoryItem().setRented(false);
		
		rentalRepo.saveAndFlush(itemRented);
		
		return itemRented;
	}
	
	// get all rentals made by a customer
	@Override
	public List<ItemRental> getCustomersRentalHistory(int customerId) {
		List<ItemRental> rentals = rentalRepo.findByCustomer_Id(customerId);
		
		Collections.sort(rentals, new Comparator<ItemRental>() {
			public int compare(ItemRental i1, ItemRental i2) {
				return -((Boolean) i1.isActive()).compareTo((Boolean) i2.isActive());
			}
		});
		
		return rentals;
	}
	
	// get a single rental by id
	@Override
	public ItemRental getOne(int id) {
		Optional<ItemRental> rental = rentalRepo.findById(id);
		if(rental.isPresent()) {
			return rental.get();
		}
		return null;
	}

	// get all rentals by an inventory item id (all the times the item had been rented)
	@Override
	public List<ItemRental> getItemRentalHistory(int itemId) {
		return rentalRepo.findByInventoryItem_Id(itemId);
	}

}

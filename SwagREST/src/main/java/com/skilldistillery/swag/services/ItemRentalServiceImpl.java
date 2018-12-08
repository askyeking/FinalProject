package com.skilldistillery.swag.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		itemRented.setCustomer(custRepo.getOne(itemRented.getCustomer().getId()));
		itemRented.setInventoryItem(itemRepo.getOne(itemRented.getInventoryItem().getId()));
		
		InventoryItem originalItem = itemRented.getInventoryItem();
		originalItem.setRented(true);
//		originalItem.getAllRents().add(itemRented);
		
		
		System.out.println(itemRented.getCustomer());
		
//		itemRepo.saveAndFlush(originalItem);
		rentalRepo.saveAndFlush(itemRented);
		
		return itemRented;
	}

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
	
	@Override
	public List<ItemRental> getCustomersRentalHistory(int customerId) {
		return rentalRepo.findByCustomer_Id(customerId);
	}
	
	@Override
	public ItemRental getOne(int id) {
		Optional<ItemRental> rental = rentalRepo.findById(id);
		if(rental.isPresent()) {
			return rental.get();
		}
		return null;
	}


	@Override
	public List<ItemRental> getItemRentalHistory(int itemId) {
		return rentalRepo.findByInventoryItem_Id(itemId);
	}

}

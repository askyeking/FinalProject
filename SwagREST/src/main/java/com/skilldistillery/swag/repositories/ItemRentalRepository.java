package com.skilldistillery.swag.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.swag.entities.ItemRental;

public interface ItemRentalRepository extends JpaRepository<ItemRental, Integer>{

	List<ItemRental> findByCustomer_Id(int customerId);
	
	
	
}

package com.skilldistillery.swag.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.swag.entities.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
	
	
	public List<Vendor> findByDisplayNameContains(String keyword);

}

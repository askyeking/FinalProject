package com.skilldistillery.swag.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.swag.entities.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

}

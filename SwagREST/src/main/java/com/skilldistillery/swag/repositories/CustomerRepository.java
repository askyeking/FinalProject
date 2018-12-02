package com.skilldistillery.swag.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.swag.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}

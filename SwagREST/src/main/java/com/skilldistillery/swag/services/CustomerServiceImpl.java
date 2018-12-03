package com.skilldistillery.swag.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.Customer;
import com.skilldistillery.swag.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Override
	public Customer show(int id) {
		
		Optional<Customer> customerOpt =  this.customerRepo.findById(id);
		if(customerOpt.isPresent()) {
			Customer customer = customerOpt.get();
			return customer;
		}
		else {
			return null;
		}
	}

}

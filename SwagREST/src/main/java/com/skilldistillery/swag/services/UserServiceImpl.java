package com.skilldistillery.swag.services;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.Customer;
import com.skilldistillery.swag.entities.User;
import com.skilldistillery.swag.entities.Vendor;
import com.skilldistillery.swag.repositories.CustomerRepository;
import com.skilldistillery.swag.repositories.UserRepository;
import com.skilldistillery.swag.repositories.VendorRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	VendorRepository vendorRepo;

	@Override
	public User show(int id) {
		User user = null;
		Optional<User> opt = userRepo.findById(id);
		if (opt.isPresent()) {
			user = opt.get();
		}

		return user;
	}

	@Override
	public User newUser(User user) {
		if (this.userRepo.existsByEmail(user.getEmail())) {
			return null;
		} else {
			this.userRepo.saveAndFlush(user);
			return user;
		}
	}

	@Override
	public User addCustomer(Customer customer) {
		if(this.userRepo.existsById(customer.getCustomerUser().getID())) {
			this.customerRepo.saveAndFlush(customer);
			return customer.getCustomerUser();
		}
		return null;
	}

	@Override
	public User addVendor(Vendor vendor) {
		if(this.userRepo.existsById(vendor.getUser().getID())) {
			this.vendorRepo.saveAndFlush(vendor);
			return vendor.getUser();
		}
		return null;
	}

	



}

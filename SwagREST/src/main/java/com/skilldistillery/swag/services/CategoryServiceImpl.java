package com.skilldistillery.swag.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.Category;
import com.skilldistillery.swag.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepo;
	
	// retrieve all categories from the database
	@Override
	public List<Category> index() {
		return this.categoryRepo.findAll();
	}

}

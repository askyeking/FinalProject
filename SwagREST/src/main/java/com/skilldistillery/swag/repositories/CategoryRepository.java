package com.skilldistillery.swag.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.swag.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
}

package com.skilldistillery.swag.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.swag.entities.Category;
import com.skilldistillery.swag.repositories.CategoryRepository;

public interface CategoryService {

	List<Category> index();	

}

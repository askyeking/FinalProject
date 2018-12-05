package com.skilldistillery.swag.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.swag.entities.Category;
import com.skilldistillery.swag.services.CategoryService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class CategoryController {
	
	@Autowired
	CategoryService catService;
	
	@GetMapping("categories")
	public List<Category> index() {
		return this.catService.index();
	}
	
	

}

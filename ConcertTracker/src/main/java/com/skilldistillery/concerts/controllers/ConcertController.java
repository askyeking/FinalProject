package com.skilldistillery.concerts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.concerts.entities.Concert;
import com.skilldistillery.concerts.services.ConcertService;

@RestController
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
public class ConcertController {
	
	@Autowired
	private ConcertService concertService;
	
	@GetMapping("concerts")
	private List<Concert> findAll(){ 
		return concertService.index();
	}

}

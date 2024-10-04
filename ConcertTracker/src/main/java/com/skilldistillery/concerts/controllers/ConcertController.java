package com.skilldistillery.concerts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.concerts.entities.Concert;
import com.skilldistillery.concerts.services.ConcertService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
public class ConcertController {
	
	@Autowired
	private ConcertService concertService;
	
	@GetMapping("concerts")
	private List<Concert> findAll(HttpServletRequest req, HttpServletResponse resp){
		return concertService.findUpcomingConcerts();
	}
	
	@PostMapping("concerts")
	private Concert createConcert(HttpServletRequest req, HttpServletResponse resp, @RequestBody Concert concert) {
		try {
			Concert newConcert = concertService.createConcert(concert);
			if(newConcert != null) {
				resp.setStatus(HttpServletResponse.SC_CREATED); //201 created
				return newConcert;
			}
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); //400 bad request
			e.printStackTrace();
		}
		return null;
	}
	
}


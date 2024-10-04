package com.skilldistillery.concerts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.concerts.projections.BandProjection;
import com.skilldistillery.concerts.projections.VenueProjection;
import com.skilldistillery.concerts.services.BandService;
import com.skilldistillery.concerts.services.VenueService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api/lookahead")
public class LookaheadController {
	
	@Autowired
	private VenueService venueService;
	@Autowired
	private BandService bandService;
	
	@GetMapping("venues/{search}")
	public List<VenueProjection> lookaheadVenues(HttpServletRequest req,HttpServletResponse resp, @PathVariable(name="search") String name) {
		return venueService.venueLookAhead(name);
	}
	@GetMapping("bands/{search}")
	public List<BandProjection> lookaheadBands(HttpServletRequest req,HttpServletResponse resp, @PathVariable(name="search") String name) {
		return bandService.bandLookAhead(name);
	}
	

}

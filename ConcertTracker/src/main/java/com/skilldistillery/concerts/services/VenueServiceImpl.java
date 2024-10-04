package com.skilldistillery.concerts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.concerts.projections.VenueProjection;
import com.skilldistillery.concerts.repositories.VenueRepository;

@Service
public class VenueServiceImpl implements VenueService{
	
	@Autowired
	private VenueRepository venueRepo;

	@Override
	public List<VenueProjection> venueLookAhead(String name) {
		return venueRepo.findByNameLike(name + '%');
	}

	
	
}

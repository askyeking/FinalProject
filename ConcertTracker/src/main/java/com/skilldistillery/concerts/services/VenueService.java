package com.skilldistillery.concerts.services;

import java.util.List;

import com.skilldistillery.concerts.projections.VenueProjection;

public interface VenueService {
	
	List<VenueProjection> venueLookAhead(String name);

}

package com.skilldistillery.concerts.services;

import java.util.List;

import com.skilldistillery.concerts.projections.BandProjection;

public interface BandService {
	
	List<BandProjection> bandLookAhead(String name);


}

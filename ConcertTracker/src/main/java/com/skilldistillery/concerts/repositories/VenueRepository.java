package com.skilldistillery.concerts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.concerts.entities.Venue;
import com.skilldistillery.concerts.projections.VenueProjection;

public interface VenueRepository extends JpaRepository<Venue, Integer> {
	
	List<VenueProjection> findByNameLike(String name);
	
	Venue findByName(String name);

}

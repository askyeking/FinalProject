package com.skilldistillery.concerts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skilldistillery.concerts.entities.Band;
import com.skilldistillery.concerts.projections.BandProjection;

public interface BandRepository extends JpaRepository<Band, Integer>{
	
	@Query("SELECT b.name FROM Band b WHERE b.name LIKE ?1")
	List<String> findNameByNameLike(String name);
	
	List<BandProjection> findByNameLike(String name);

	Band findByName(String name);


}

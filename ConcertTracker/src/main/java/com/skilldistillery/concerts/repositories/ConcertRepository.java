package com.skilldistillery.concerts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.concerts.entities.Concert;

public interface ConcertRepository extends JpaRepository<Concert, Integer>{
	
}

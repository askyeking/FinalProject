package com.skilldistillery.concerts.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.concerts.entities.Concert;

public interface ConcertRepository extends JpaRepository<Concert, Integer>{
	
	List<Concert> findByDayOfEventAfterOrderByDayOfEvent(LocalDate date);
}

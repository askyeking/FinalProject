package com.skilldistillery.concerts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.concerts.entities.ConcertAct;
import com.skilldistillery.concerts.entities.ConcertActId;

public interface ConcertActRepository extends JpaRepository<ConcertAct, ConcertActId> {
	
	List<ConcertAct> findByConcertId(int id);

}

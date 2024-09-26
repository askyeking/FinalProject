package com.skilldistillery.concerts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.concerts.entities.Concert;
import com.skilldistillery.concerts.repositories.ConcertRepository;

@Service
public class ConcertServiceImpl implements ConcertService{
	
	@Autowired
	private ConcertRepository concertRepo;

	@Override
	public List<Concert> index() {
		// TODO Auto-generated method stub
		return concertRepo.findAll();
	}

}

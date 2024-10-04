package com.skilldistillery.concerts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.concerts.projections.BandProjection;
import com.skilldistillery.concerts.repositories.BandRepository;

@Service
public class BandServiceImpl implements BandService{
	
	@Autowired
	private BandRepository bandRepo;

	@Override
	public List<BandProjection> bandLookAhead(String name) {
		return bandRepo.findByNameLike(name + "%");
	}

}

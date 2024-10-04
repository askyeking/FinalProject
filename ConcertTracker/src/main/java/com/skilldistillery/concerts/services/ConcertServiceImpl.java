package com.skilldistillery.concerts.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.concerts.entities.Band;
import com.skilldistillery.concerts.entities.Concert;
import com.skilldistillery.concerts.entities.ConcertAct;
import com.skilldistillery.concerts.entities.ConcertActId;
import com.skilldistillery.concerts.entities.Venue;
import com.skilldistillery.concerts.repositories.BandRepository;
import com.skilldistillery.concerts.repositories.ConcertActRepository;
import com.skilldistillery.concerts.repositories.ConcertRepository;
import com.skilldistillery.concerts.repositories.VenueRepository;

@Service
public class ConcertServiceImpl implements ConcertService {
	
	@Autowired
	private ConcertRepository concertRepo;
	
	@Autowired
	private VenueRepository venueRepo;
	
	@Autowired
	private BandRepository bandRepo;
	
	@Autowired
	private ConcertActRepository concertActRepo;

	@Override
	public List<Concert> index() {
		return concertRepo.findAll();
	}

	@Override
	public List<Concert> findUpcomingConcerts() {
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		return concertRepo.findByDayOfEventAfterOrderByDayOfEvent(yesterday);
	}

	@Override
	public Concert createConcert(Concert concert) {
		Venue managedVenue = venueRepo.findByName(concert.getVenue().getName());
		
		if(managedVenue != null) {
			concert.setVenue(managedVenue);
			concertRepo.saveAndFlush(concert);
			generateVenueActIds(concert);
			return concert;
		}
		return null;
	}
	
	private void generateVenueActIds(Concert concert) {
		
		List<ConcertAct> acts = concert.getBands();
		
		for (ConcertAct concertAct : acts) {
			
			Band managedBand = bandRepo.findByName(concertAct.getBand().getName());
			ConcertActId id = new ConcertActId(concert.getId(),managedBand.getId());
			concertAct.setId(id);
			concertAct.setConcert(concert);
			concertAct.setBand(managedBand);
			
			concertActRepo.saveAndFlush(concertAct);
		}
		
	}
	
}

package com.skilldistillery.concerts.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class ConcertActTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private ConcertAct concertAct;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	    emf = Persistence.createEntityManagerFactory("ConcertsJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	    emf.close();
	}
	
	@BeforeEach
	void setUp() throws Exception {
	    em = emf.createEntityManager();
	    ConcertActId id = new ConcertActId(1,1);
	    
	    concertAct = em.find(ConcertAct.class, id);
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	}

	@Test
	void test_User_basic_mappings() {
		assertNotNull(concertAct);
		assertEquals(1, concertAct.getLineupPosition());
		
	}
	
	@Test
	void ConcertAct_has_concert() {
		assertNotNull(concertAct.getConcert());
		
	}
	@Test
	void ConcertAct_has_band() {
		assertNotNull(concertAct.getBand());
		
	}

}

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

class BandTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Band band;
	
	
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
	    band = em.find(Band.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	}

	@Test
	void test_User_basic_mappings() {
		assertNotNull(band);
		assertEquals("Fontaines D.C.", band.getName());
		
	}

}

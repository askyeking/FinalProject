package com.skilldistillery.swag.entities;


import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Due to constant fluctuations in the DB, tests may not pass, even though the mapping is working correctly
//TODO and lesson learned: create mock data that can stay constant for testing purposes.
class RatingOfVendorTests {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private RatingOfVendor rating;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("rentaswag");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		rating = em.find(RatingOfVendor.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test() {
		assertEquals(1, rating.getItemRental().getId());
		assertEquals(1, rating.getCustomer().getId());
		assertEquals(3, rating.getRating());
		assertEquals("He give da man, me, 4 stars, I give him 3!", rating.getComment());
		
	}

}

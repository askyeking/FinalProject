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

class RatingOfCustomerTests {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private RatingOfCustomer rating;
	
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
		rating = em.find(RatingOfCustomer.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_RatingOfCustomer_mapping() {
		assertEquals(1, rating.getItemRental().getId());
		assertEquals(1, rating.getVendor().getId());
		assertEquals(4, rating.getRating());
		assertEquals("Decent experience", rating.getComment());
	}

}

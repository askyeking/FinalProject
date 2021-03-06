package com.skilldistillery.swag.entities;

import static org.junit.jupiter.api.Assertions.*;

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
class VendorTests {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Vendor vendor;
	
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
		vendor = em.find(Vendor.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		vendor = null;
	}
	
	
	@Test
	void test() {
		assertEquals(1, vendor.getId());
		assertTrue(vendor.isActive());
		assertEquals(2, vendor.getUser().getID());
		assertEquals("I sell swag + you buy swag = We Swag, bro!", vendor.getAbout());
		assertEquals("theSeller", vendor.getDisplayName());
		assertNull(vendor.getImgUrl());
	}

}

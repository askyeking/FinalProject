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

class InventoryItemTests {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private InventoryItem item;
	
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
		item = em.find(InventoryItem.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_InventoryItem_mapping() {
<<<<<<< HEAD
		assertEquals(1, item.getItemCategories().size());
=======

>>>>>>> f3ddb96cf5fa3e806f2298f27f3dfeff04e9282e
		assertEquals("Page Boy Hat", item.getName());
		assertEquals(4, item.getPrice());
		assertEquals(1, item.getVendor().getId());
		assertEquals("A nice hat all year round, keeps the sun out of your eyes or can provide some extra warmth in the winter.", item.getDescription());
		assertEquals("https://images-na.ssl-images-amazon.com/images/I/61KToCWpZSL._UY445_.jpg", item.getImgUrl());
		assertEquals("Good", item.getCondition());
		assertEquals("theSeller!!", item.getVendor().getDisplayName());
		assertEquals(true, item.isActive());
		assertEquals(false, item.isRented());
		assertEquals(1, item.getAllRents().get(0).getId());
	}

}

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

class ItemRentalTests {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private ItemRental rental;
	
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
		rental = em.find(ItemRental.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_ItemRental_mapping() {
//		assertEquals(1, rental.getInventoryItem().getId());
//		assertEquals(1, rental.getCustomer().getId());getClass();
//		assertEquals(true, rental.isPaid());
//		assertEquals("2018-12-13", rental.getStartDate().toString());
//		assertEquals("2018-12-14", rental.getEndDate().toString());
//		assertEquals(56, rental.getPaidAmount());
//		assertEquals(true, rental.isActive());
//		assertEquals("send it to me address mate. I'll pay ya. That stuff is real swag!", rental.getTransactionInfo());
//		assertEquals("effrom23", rental.getCustomer().getDisplayName());
		assertEquals(1, rental.getCustomerComments().size());
		assertEquals(1, rental.getVendorComments().size());
	}

}

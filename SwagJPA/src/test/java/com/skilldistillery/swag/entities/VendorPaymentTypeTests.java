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

class VendorPaymentTypeTests {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private VendorPaymentType vendPayType;
	
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
		vendPayType = em.find(VendorPaymentType.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_VendorPaymentType_mapping() {
		assertEquals(1, vendPayType.getVendor().getId());
		assertEquals(1, vendPayType.getPaymentType().getId());
		assertEquals("1234567899999999", vendPayType.getAcctNo());
		assertEquals("2025-11-11", vendPayType.getExpirationDate().toString());
		assertEquals("Bro Bro", vendPayType.getCardHolderName());
	}

}

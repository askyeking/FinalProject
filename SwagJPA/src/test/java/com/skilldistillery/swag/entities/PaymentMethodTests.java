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
class PaymentMethodTests {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private PaymentMethod payMethod;
	
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
		payMethod = em.find(PaymentMethod.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_PaymentMethod_mapping() {
		assertEquals(1, payMethod.getPaymentType().getId());
		assertEquals(1, payMethod.getCustomer().getId());
		assertEquals("Da card", payMethod.getName());
		assertEquals("123456789102300", payMethod.getAcctNo());
		assertEquals("223", payMethod.getCvc());
		assertEquals("11/2023", payMethod.getExpirationDate());
		assertEquals("Bruce Springsteen", payMethod.getCardHolderName());
	}

}

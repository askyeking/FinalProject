package com.skilldistillery.swag.entities;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTests {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;
	
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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test() {
		assertEquals(1, user.getID());
		assertEquals("customer", user.getPassword());
		assertEquals("customer@user.com", user.getEmail());
		assertTrue(user.isActive());
		assertEquals("standard", user.getRole());
		
		user = em.find(User.class, 2);
		assertEquals(2, user.getID());
		assertEquals("vendorcustomer", user.getPassword());
		assertEquals("vendor@user.com", user.getEmail());
		assertTrue(user.isActive());
		assertEquals("standard", user.getRole());
		
	}

}

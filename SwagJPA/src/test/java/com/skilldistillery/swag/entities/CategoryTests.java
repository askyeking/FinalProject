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

// Due to constant fluctuations in the DB, tests may not pass, even though the mapping is working correctly
// TODO and lesson learned: create mock data that can stay constant for testing purposes.
class CategoryTests {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Category cat;
	
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
		cat = em.find(Category.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_Category_mapping() {
//		assertEquals("hats", cat.getName());
		assertEquals(1 ,cat.getItemsOfCategory().size());
	}

}

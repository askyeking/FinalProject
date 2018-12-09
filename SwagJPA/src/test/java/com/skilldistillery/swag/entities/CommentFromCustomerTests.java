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

class CommentFromCustomerTests {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private CommentFromCustomer custComment;
	
	
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
		custComment = em.find(CommentFromCustomer.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}
	
	@Test
	void test() {
		assertEquals(1, custComment.getId());
		assertEquals(1, custComment.getCustomer().getId());
		assertEquals(1, custComment.getItemRental().getId());
		assertEquals("Cool dude", custComment.getComment());
		assertEquals("2018-11-02 10:29:14.0", custComment.getPostDate().toString());
	}

}

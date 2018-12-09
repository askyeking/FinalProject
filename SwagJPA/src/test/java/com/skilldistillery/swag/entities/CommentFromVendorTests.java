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

class CommentFromVendorTests {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private CommentFromVendor vendorComment;
	
	
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
		vendorComment = em.find(CommentFromVendor.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}
	
	@Test
	void test() {
		assertEquals(1, vendorComment.getId());
		assertEquals(1, vendorComment.getPoster().getId());
		assertEquals(1, vendorComment.getItemRental().getId());
		assertEquals("cool, cool", vendorComment.getComment());
		assertEquals("2018-11-02 10:29:42.0", vendorComment.getPostDate().toString());
	}

}

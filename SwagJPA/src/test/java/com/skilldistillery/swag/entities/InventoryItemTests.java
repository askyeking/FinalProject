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
//		assertEquals("swagger hat for swaggy men", item.getName());
//		assertEquals(4, item.getPrice());
//		assertEquals(1, item.getVendor().getId());
//		assertEquals("get that swag bro! I know you want it, you know I want you.", item.getDescription());
//		assertEquals("https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjI097ekv3e"
//				+ "AhXI6oMKHdE4AW4QjRx6BAgBEAU&url=https%3A%2F%2Fwww.gangstagroup.com%2Fgangstagroup-sorry-im-"
//				+ "swag-snapback-cap-navy-red%2F&psig=AOvVaw0K8zMcx0wimPAOr3YTc7bE&ust=1543702665988705", item.getImgUrl());
//		assertEquals("good", item.getCondition());
//		assertEquals("theSeller", item.getVendor().getDisplayName());
//		assertEquals(true, item.isActive());
//		assertEquals(false, item.isRented());
		assertEquals(1, item.getItemCategories().size());
	}

}

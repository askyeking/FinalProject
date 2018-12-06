package com.skilldistillery.swag.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.swag.entities.InventoryItem;
import com.skilldistillery.swag.entities.Vendor;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {
	Set<InventoryItem> findByIsRentedAndIsActive(boolean isRented, boolean isActive);
	
	List<InventoryItem> findByVendor(Vendor vendor);
	
	@Query("select i from InventoryItem i JOIN FETCH i.itemCategories ic where ic.name = :category")
	List<InventoryItem> findByItemCategory(@Param("category") String category);
	
	List<InventoryItem> findByNameContains(String keyword);
}

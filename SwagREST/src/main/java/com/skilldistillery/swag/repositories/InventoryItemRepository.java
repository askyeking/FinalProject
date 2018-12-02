package com.skilldistillery.swag.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.swag.entities.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {
	Set<InventoryItem> findByIsRentedAndIsActive(boolean isRented, boolean isActive);
}

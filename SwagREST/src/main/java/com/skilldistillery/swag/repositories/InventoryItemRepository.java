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
	
	
	//native query for the jpql for findByItemCategory
//	select * from inventory_item 
//	join inventory_item_category on inventory_item.id = inventory_item_category.inventory_item_id
//	join category on category.id = inventory_item_category.category_id where category.name = 'formal';
	
	@Query("select i from inventory_item i JOIN FETCH i.itemCategories ic where ic.name = :category")
	List<InventoryItem> findByItemCategory(@Param("category")String category);
	List<InventoryItem> findByNameLikeOrDescriptionLike(String keyword, String keyword2);
}

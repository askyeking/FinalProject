package com.skilldistillery.swag.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="inventory_item")
public class InventoryItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="vendor_id")
	private Vendor vendor;
	
	private double price;
	@Column(name="item_condition")
	
	private String condition;
	
	private String name;
	
	private String description;
	
	@Column(name="image_url")
	private String imgUrl;
	
	@Column(name="active")
	private boolean isActive;
	
	@Column(name="rented")
	private boolean isRented;
	
	
	
	@ManyToMany
	@JoinTable(name="inventory_item_category",
	joinColumns=@JoinColumn(name="inventory_item_id"),
	inverseJoinColumns=@JoinColumn(name="category_id"))
	private List<Category> itemCategories;
	
	
//	@ManyToMany
//	@JoinTable(name="favorite_recipe",
//	joinColumns=@JoinColumn(name="recipe_id"),
//	inverseJoinColumns=@JoinColumn(name="user_id"))
//	private List<User> usersWhoFavorited;
		
	
	

	@JsonIgnore
	@OneToMany(mappedBy="inventoryItem")
	private List<ItemRental> allRents;
	
	
	public Vendor getVendor() {
		return vendor;
	}
	
	public List<Category> getItemCategories() {
		return itemCategories;
	}

	public void setItemCategories(List<Category> itemCategories) {
		this.itemCategories = itemCategories;
	}

	
	public List<ItemRental> getAllRents() {
		return allRents;
	}


	public void setAllRents(List<ItemRental> allRents) {
		this.allRents = allRents;
	}


	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isRented() {
		return isRented;
	}

	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryItem other = (InventoryItem) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InventoryItem [id=" + id + ", vendor=" + vendor + ", price=" + price + ", condition=" + condition
				+ ", name=" + name + ", description=" + description + ", imgUrl=" + imgUrl + ", isActive=" + isActive
				+ ", isRented=" + isRented + "]";
	}
	
	public InventoryItem() {
		
	}

	public InventoryItem(int id, Vendor vendor, double price, String condition, String name, String description,
			String imgUrl, boolean isActive, boolean isRented) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.price = price;
		this.condition = condition;
		this.name = name;
		this.description = description;
		this.imgUrl = imgUrl;
		this.isActive = isActive;
		this.isRented = isRented;
	}

	
	
	

}

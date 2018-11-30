package com.skilldistillery.swag.entities;

public class InventoryItem {
	
	private int id; 
	
//	private int vendorId;
	
	private double price;
	
	//change data type to ENUM
	private String condition;
	
	private String name;
	
	private String description;
	
	private String imgUrl;
	
	private boolean isActive;
	
	private boolean isRented;
	

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
		return "InventoryItem [id=" + id + ", price=" + price + ", condition=" + condition + ", name=" + name
				+ ", description=" + description + ", imgUrl=" + imgUrl + ", isActive=" + isActive + ", isRented="
				+ isRented + "]";
	}
	
	public InventoryItem() {
		
	}

	public InventoryItem(int id, double price, String condition, String name, String description, String imgUrl,
			boolean isActive, boolean isRented) {
		super();
		this.id = id;
		this.price = price;
		this.condition = condition;
		this.name = name;
		this.description = description;
		this.imgUrl = imgUrl;
		this.isActive = isActive;
		this.isRented = isRented;
	}
	
	

}

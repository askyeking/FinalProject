package com.skilldistillery.swag.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * RatingOfCustomer entity
 * ** Not currently used by the front-end, but tested via Postman **
 * Mapped fields to the table in the DB using JPA and Hibernate.
 * Methods are standard getters and setters, hashcode and equals (using unique ID) and toString
 */
@Entity
@Table(name="rating_of_customer")
public class RatingOfCustomer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int rating;
	
	private String comment;
	
	@OneToOne
	@JoinColumn(name="item_rental_id")
	private ItemRental itemRental;
	
	@OneToOne
	@JoinColumn(name="vendor_id")
	private Vendor vendor;
	

	public ItemRental getItemRental() {
		return itemRental;
	}

	public void setItemRental(ItemRental itemRental) {
		this.itemRental = itemRental;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public int getId() {
		return id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
		RatingOfCustomer other = (RatingOfCustomer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RatingOfCustomer [id=" + id + ", rating=" + rating + ", comment=" + comment + ", itemRental="
				+ itemRental + ", vendor=" + vendor + "]";
	}

	

	public RatingOfCustomer(int id, int rating, String comment, ItemRental itemRental, Vendor vendor) {
		super();
		this.id = id;
		this.rating = rating;
		this.comment = comment;
		this.itemRental = itemRental;
		this.vendor = vendor;
	}

	public RatingOfCustomer() {
		super();
	}
	
	

}

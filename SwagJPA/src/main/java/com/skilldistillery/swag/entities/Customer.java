package com.skilldistillery.swag.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


/*
 * Customer object
 * Mapped fields to the table in the DB using JPA and Hibernate.
 * Methods are standard getters and setters, hashcode and equals (using unique ID) and toString
 */
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="display_name")
	private String displayName;
	
	@Column(name="active")
	private boolean isActive;
	
	@Column(name="avatar_url")
	private String avatarURL;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="user_id")
	private User customerUser;
	
	@JsonIgnore
	@OneToMany(mappedBy="poster")
	private List<CommentFromCustomer> comments;
	
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<ItemRental> rentedItems;
	
	
	
	

	public List<CommentFromCustomer> getComments() {
		return comments;
	}

	public void setComments(List<CommentFromCustomer> comments) {
		this.comments = comments;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ItemRental> getRentedItems() {
		return rentedItems;
	}

	public void setRentedItems(List<ItemRental> rentedItems) {
		this.rentedItems = rentedItems;
	}

	public User getCustomerUser() {
		return customerUser;
	}

	public void setCustomerUser(User customerUser) {
		this.customerUser = customerUser;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

	public int getId() {
		return id;
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
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", displayName=" + displayName + ", isActive=" + isActive + ", avatarURL="
				+ avatarURL + ", customerUser=" + customerUser + "]";
	}

	

	public Customer(int id, String displayName, boolean isActive, String avatarURL, User userCustomer) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.isActive = isActive;
		this.avatarURL = avatarURL;
		this.customerUser = userCustomer;
	}

	public Customer() {
		super();
	}
	
	
	
	
	
	
}

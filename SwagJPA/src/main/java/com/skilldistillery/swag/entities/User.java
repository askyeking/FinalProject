package com.skilldistillery.swag.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * User entity
 * Mapped fields to the table in the DB using JPA and Hibernate.
 * Methods are standard getters and setters, hashcode and equals (using unique ID) and toString
 */
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID;
	
	private String password;
	
	private String email;
	
	@Column(name="active")
	private boolean isActive;
	
	@OneToOne(mappedBy="user")
	private Vendor vendor;
	
	@OneToOne(mappedBy="customerUser")
	private Customer customer;
	
	private String role;
	
	
//	private String fName;
//	private String lName;
	
	
	
	
	public int getID() {
		return ID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
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
		User other = (User) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [ID=" + ID + ", password=" + password + ", email=" + email + ", isActive=" + isActive + ", role="
				+ role + "customer" + "]";
	}
	
	
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	
	public User(int iD, String password, String email, boolean isActive, Vendor vendor, Customer customer,
			String role) {
		super();
		ID = iD;
		this.password = password;
		this.email = email;
		this.isActive = isActive;
		this.vendor = vendor;
		this.customer = customer;
		this.role = role;
	}
	public User(int iD, String password, String email, boolean isActive, String role, String fName, String lName) {
		super();
		ID = iD;
		this.password = password;
		this.email = email;
		this.isActive = isActive;
		this.role = role;
	}
	public User() {
		super();
	}
	
	
	
	

}

package com.skilldistillery.swag.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * ItemRental entity
 * Mapped fields to the table in the DB using JPA and Hibernate.
 * Methods are standard getters and setters, hashcode and equals (using unique ID) and toString
 */
@Entity
@Table(name="item_rental")
public class ItemRental {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="inventory_item_id")
	private InventoryItem inventoryItem;
	
//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy="itemRental")
	public List<CommentFromCustomer> customerComments;
	
	@OneToMany(mappedBy="itemRental")
	public List<CommentFromVendor> vendorComments;
	
	@Column(name="paid")
	private boolean isPaid;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="paid_amount")
	private double paidAmount;;
	
	@Column(name="active")
	private boolean isActive;
	
	@Column(name="transaction_info")
	private String transactionInfo;
	

	
	
	public List<CommentFromCustomer> getCustomerComments() {
		return customerComments;
	}

	public void setCustomerComments(List<CommentFromCustomer> customerComments) {
		this.customerComments = customerComments;
	}

	public List<CommentFromVendor> getVendorComments() {
		return vendorComments;
	}

	public void setVendorComments(List<CommentFromVendor> vendorComments) {
		this.vendorComments = vendorComments;
	}

	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}

	public void setInventoryItem(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
	}
	
	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getTransactionInfo() {
		return transactionInfo;
	}

	public void setTransactionInfo(String transactionInfo) {
		this.transactionInfo = transactionInfo;
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
		ItemRental other = (ItemRental) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemRental [id=" + id + ", inventoryItem=" + inventoryItem + ", customer=" + customer + ", isPaid="
				+ isPaid + ", startDate=" + startDate + ", endDate=" + endDate + ", paidAmount=" + paidAmount
				+ ", isActive=" + isActive + ", transactionInfo=" + transactionInfo + "]";
	}
	
	public ItemRental() {
		
	}

	public ItemRental(int id, InventoryItem inventoryItem, Customer customer, boolean isPaid, Date startDate,
			Date endDate, double paidAmount, boolean isActive, String transactionInfo) {
		super();
		this.id = id;
		this.inventoryItem = inventoryItem;
		this.customer = customer;
		this.isPaid = isPaid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.paidAmount = paidAmount;
		this.isActive = isActive;
		this.transactionInfo = transactionInfo;
	}

	
	

}
